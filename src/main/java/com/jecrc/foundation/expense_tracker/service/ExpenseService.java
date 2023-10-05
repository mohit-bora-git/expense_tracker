package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.constants.HttpSuccessMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.ExpenseDAO;
import com.jecrc.foundation.expense_tracker.dao.UserDAO;
import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.enums.Months;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ExpenseService {

  @Autowired
  private ExpenseDAO expenseDAO;

  @Autowired
  private UserDAO userDAO;

  @Async("asyncExecutor")
  public void createExpense(Long id, ExpenseDO expenseDO,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      expenseDAO.save(DataConverter.convertExpenseDoToExpenseDbo(expenseDO, id));
      response.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.EXPENSE_SUCCESSFULLY_SAVED)));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void updateExpense(Long id, ExpenseDO expenseDO,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      ExpenseDBO existingExpense = expenseDAO.findById(expenseDO.getId());
      if (Objects.isNull(existingExpense)) {
        log.info("Expense not exists for the corresponding expenseId");
        response.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseErrorCode.EXPENSE_NOT_FOUND,
                HttpResponseErrorMessage.EXPENSE_NOT_FOUND)));
        return;
      }
      expenseDAO.update(DataConverter.convertExpenseDoToExpenseDbo(expenseDO, id));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          HttpSuccessMessage.EXPENSE_SUCCESSFULLY_UPDATED)));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getExpense(Long expenseId, CompletableFuture<ResponseEntity<?>> response) {
    try {
      ExpenseDBO expenseDBO = expenseDAO.findById(expenseId);
      if (Objects.isNull(expenseDBO)) {
        log.info("Expense not exists for the corresponding expenseId");
        response.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseErrorCode.EXPENSE_NOT_FOUND,
                HttpResponseErrorMessage.EXPENSE_NOT_FOUND)));
        return;
      }
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          DataConverter.convertExpenseDboToExpenseDo(expenseDBO))));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getMonthlyExpense(Long id, String month, Integer year,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      LocalDate startLocalDate = LocalDate.of(year, Months.getMonthNumber(month), 1);
      LocalDate endLocalDate = LocalDate.of(year, Months.getMonthNumber(month),
          Months.getMonthDays(year, Months.getMonth(month)));
      List<ExpenseDBO> expenses = expenseDAO.findExpensesByDateRange(Date.valueOf(startLocalDate),
          Date.valueOf(endLocalDate), id);
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenses.stream().map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getYearlyExpense(Long id, Integer year,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      LocalDate startLocalDate = LocalDate.of(year, Months.JAN.number, 1);
      LocalDate endLocalDate =
          LocalDate.of(year, Months.DEC.number, Months.getMonthDays(year, Months.DEC));
      List<ExpenseDBO> expenses = expenseDAO.findExpensesByDateRange(Date.valueOf(startLocalDate),
          Date.valueOf(endLocalDate), id);
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenses.stream().map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getRangeExpense(Long id, Long startDate, Long endDate,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      List<ExpenseDBO> expenses =
          expenseDAO.findExpensesByDateRange(new Date(startDate), new Date(endDate), id);
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenses.stream().map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getExpenseByDate(Long id, Long date, CompletableFuture<ResponseEntity<?>> response) {
    try {
      List<ExpenseDBO> expenses = expenseDAO.findExpenseByDate(new Date(date), id);
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          expenses.stream().map(DataConverter::convertExpenseDboToExpenseDo))));
    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }
}
