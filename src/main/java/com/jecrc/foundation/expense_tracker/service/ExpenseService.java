package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.ExpenseDao;
import com.jecrc.foundation.expense_tracker.dbos.ExpenseDbo;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDo;
import com.jecrc.foundation.expense_tracker.dos.UserDo;
import com.jecrc.foundation.expense_tracker.enums.Months;
import com.jecrc.foundation.expense_tracker.helper_service.TransactionAlert;
import com.jecrc.foundation.expense_tracker.utils.DateTimeUtils;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
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

  private final ExpenseDao expenseDAO;
  private final TransactionAlert transactionAlert;

  @Autowired
  public ExpenseService(ExpenseDao expenseDAO, TransactionAlert transactionAlert) {
    this.expenseDAO = expenseDAO;
    this.transactionAlert = transactionAlert;
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void createExpense(UserDo user, ExpenseDo expenseDO,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      LocalDateTime currentDate = DateTimeUtils.getCurrentDate();
      Date todayDate = new Date(DateTimeUtils.getCurrentDateInMillis(currentDate));
      Date firstDateOfMonth = new Date(DateTimeUtils.getMonthFirstDateInMillis());
      Double totalTransaction =
          expenseDAO.findTotalMonthlyExpense(user.getId(), firstDateOfMonth, todayDate);
      if (user.getTransactionLimit() != null && totalTransaction + expenseDO.getTransactionAmount() > user.getTransactionLimit()) {
        transactionAlert.sendEmail(user,
            totalTransaction + expenseDO.getTransactionAmount() - user.getTransactionLimit());
      }
      expenseDAO.save(DataConverter.convertExpenseDoToExpenseDbo(expenseDO, user.getId()));
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          HttpResponseMessage.EXPENSE_SUCCESSFULLY_SAVED)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void updateExpense(UserDo user, ExpenseDo expenseDO,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      ExpenseDbo existingExpense = expenseDAO.findById(expenseDO.getId());
      if (Objects.isNull(existingExpense)) {
        log.info("Expense not exists for the corresponding expenseId");
        responseFuture.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseCode.EXPENSE_NOT_FOUND,
                HttpResponseMessage.EXPENSE_NOT_FOUND)));
        return;
      }
      expenseDAO.update(DataConverter.convertExpenseDoToExpenseDbo(expenseDO, user.getId()));
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          HttpResponseMessage.EXPENSE_SUCCESSFULLY_UPDATED)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getExpense(Long expenseId, CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      ExpenseDbo expenseDBO = expenseDAO.findById(expenseId);
      if (Objects.isNull(expenseDBO)) {
        log.info("Expense not exists for the corresponding expenseId");
        responseFuture.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseCode.EXPENSE_NOT_FOUND,
                HttpResponseMessage.EXPENSE_NOT_FOUND)));
        return;
      }
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          DataConverter.convertExpenseDboToExpenseDo(expenseDBO))));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getMonthlyExpense(Long id, String month, Integer year,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      LocalDate startLocalDate = LocalDate.of(year, Months.getMonthNumber(month), 1);
      LocalDate endLocalDate = LocalDate.of(year, Months.getMonthNumber(month),
          Months.getMonthDays(year, Months.getMonth(month)));
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenseDAO.findExpensesByDateRange(Date.valueOf(startLocalDate),
                  Date.valueOf(endLocalDate), id).stream()
              .map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getYearlyExpense(Long id, Integer year,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      LocalDate startLocalDate = LocalDate.of(year, Months.JAN.getNumber(), 1);
      LocalDate endLocalDate =
          LocalDate.of(year, Months.DEC.getNumber(), Months.getMonthDays(year, Months.DEC));
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenseDAO.findExpensesByDateRange(Date.valueOf(startLocalDate),
                  Date.valueOf(endLocalDate), id).stream()
              .map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getRangeExpense(Long id, Long startDate, Long endDate,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenseDAO.findExpensesByDateRange(new Date(startDate), new Date(endDate), id).stream()
              .map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getExpenseByDate(Long id, Long date,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenseDAO.findExpenseByDate(new Date(date), id).stream()
              .map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }
}
