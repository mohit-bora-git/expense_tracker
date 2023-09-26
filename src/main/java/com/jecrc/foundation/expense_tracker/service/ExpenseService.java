package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dao.ExpenseDAO;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ExpenseService {

  @Autowired
  private ExpenseDAO expenseDao;

  @Async("asyncExecutor")
  public void createExpense(ExpenseDO expenseDo, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void updateExpense(ExpenseDO expenseDo, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void getMonthlyExpense(String month, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void getYearlyExpense(Integer year, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void getRangeExpense(Date startDate, Date endDate,
      CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }
}
