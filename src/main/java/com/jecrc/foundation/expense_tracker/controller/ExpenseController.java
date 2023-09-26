package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.service.ExpenseService;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import com.jecrc.foundation.expense_tracker.validator.ExpenseValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.sql.Date;


@RestController
@RequestMapping("/api/expense")
@Slf4j
public class ExpenseController extends BaseController {

  @Autowired
  private ExpenseService expenseService;

  @Autowired
  private ExpenseValidator expenseValidator;

  @PostMapping(value = "/create_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> createExpense(@RequestBody ExpenseDO expenseDo,
      HttpServletRequest requestHeaders) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/v1/api/expense/create_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {

    } catch (Exception e) {
      log.error("Create expense request for createExpense failed due to : {}",
          StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
    return df;
  }

  @PatchMapping(value = "/update_expense/{expenseId}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateExpense(@PathVariable("expenseId") Long expenseId,
      HttpServletRequest requestHeaders) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/update_expense/{expenseId}";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {

    } catch (Exception e) {
      log.error("Update expense request failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
    return df;
  }

  @GetMapping(value = "/get_monthly_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getExpenses(@RequestParam("month") String month,
      HttpServletRequest requestHeaders) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_monthly_expenses";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {

    } catch (Exception e) {
      log.error("Get monthly expense request  failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
    return df;
  }

  @GetMapping(value = "/get_yearly_expense", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getYearlyExpense(@RequestParam("year") Integer year,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_yearly_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {

    } catch (Exception e) {
      log.error("Get yearly expense request  failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
    return df;
  }

  @GetMapping(value = "/get_range_expense", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getRangeExpense(
      @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_range_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {

    } catch (Exception e) {
      log.error("Get range expense request failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
    return df;
  }
}
