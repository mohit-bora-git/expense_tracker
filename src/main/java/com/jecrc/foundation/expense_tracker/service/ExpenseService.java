package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.constants.HttpSuccessMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.ExpenseDAO;
import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;
import com.jecrc.foundation.expense_tracker.enums.Months;
import com.jecrc.foundation.expense_tracker.helper_service.EmailService;
import com.jecrc.foundation.expense_tracker.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ExpenseService {

  @Autowired
  private ExpenseDAO expenseDAO;

  @Autowired
  private EmailService emailService;

  @Async(AsyncConfig.API_EXECUTOR)
  public void createExpense(UserDO user, ExpenseDO expenseDO,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    LocalDateTime currentDate = DateTimeUtils.getCurrentDate();
    Date todayDate = new Date(DateTimeUtils.getCurrentDateInMillis(currentDate));
    Date firstDateOfMonth = new Date(DateTimeUtils.getMonthFirstDateInMillis());
    Double totalTransaction =
        expenseDAO.findTotalMonthlyExpense(user.getId(), firstDateOfMonth, todayDate);
    if (user.getTransactionLimit() != null && totalTransaction + expenseDO.getTransactionAmount() > user.getTransactionLimit()) {
      emailService.sendEmail(user,
          totalTransaction + expenseDO.getTransactionAmount() - user.getTransactionLimit());
    }
    expenseDAO.save(DataConverter.convertExpenseDoToExpenseDbo(expenseDO, user.getId()));
    responseFuture.complete(ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.EXPENSE_SUCCESSFULLY_SAVED)));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void updateExpense(UserDO user, ExpenseDO expenseDO,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    ExpenseDBO existingExpense = expenseDAO.findById(expenseDO.getId());
    if (Objects.isNull(existingExpense)) {
      log.info("Expense not exists for the corresponding expenseId");
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseErrorCode.EXPENSE_NOT_FOUND,
              HttpResponseErrorMessage.EXPENSE_NOT_FOUND)));
      return;
    }
    expenseDAO.update(DataConverter.convertExpenseDoToExpenseDbo(expenseDO, user.getId()));
    responseFuture.complete(ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.EXPENSE_SUCCESSFULLY_UPDATED)));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getExpense(Long expenseId, CompletableFuture<ResponseEntity<?>> responseFuture) {
    ExpenseDBO expenseDBO = expenseDAO.findById(expenseId);
    if (Objects.isNull(expenseDBO)) {
      log.info("Expense not exists for the corresponding expenseId");
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseErrorCode.EXPENSE_NOT_FOUND,
              HttpResponseErrorMessage.EXPENSE_NOT_FOUND)));
      return;
    }
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        DataConverter.convertExpenseDboToExpenseDo(expenseDBO))));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getMonthlyExpense(Long id, String month, Integer year,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    LocalDate startLocalDate = LocalDate.of(year, Months.getMonthNumber(month), 1);
    LocalDate endLocalDate = LocalDate.of(year, Months.getMonthNumber(month),
        Months.getMonthDays(year, Months.getMonth(month)));
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        expenseDAO.findExpensesByDateRange(Date.valueOf(startLocalDate), Date.valueOf(endLocalDate),
            id).stream().map(DataConverter::convertExpenseDboToExpenseDo))));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getYearlyExpense(Long id, Integer year,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    LocalDate startLocalDate = LocalDate.of(year, Months.JAN.getNumber(), 1);
    LocalDate endLocalDate =
        LocalDate.of(year, Months.DEC.getNumber(), Months.getMonthDays(year, Months.DEC));
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        expenseDAO.findExpensesByDateRange(Date.valueOf(startLocalDate), Date.valueOf(endLocalDate),
            id).stream().map(DataConverter::convertExpenseDboToExpenseDo))));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getRangeExpense(Long id, Long startDate, Long endDate,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        expenseDAO.findExpensesByDateRange(new Date(startDate), new Date(endDate), id).stream()
            .map(DataConverter::convertExpenseDboToExpenseDo))));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getExpenseByDate(Long id, Long date,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        expenseDAO.findExpenseByDate(new Date(date), id).stream()
            .map(DataConverter::convertExpenseDboToExpenseDo))));
  }
}
