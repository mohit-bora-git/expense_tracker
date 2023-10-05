package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;
import com.jecrc.foundation.expense_tracker.helper_service.AccessTokenService;
import com.jecrc.foundation.expense_tracker.service.ExpenseService;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import com.jecrc.foundation.expense_tracker.validator.ExpenseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/expense")
@Slf4j
public class ExpenseController extends BaseController {

  @Autowired
  private ExpenseService expenseService;

  @Autowired
  private ExpenseValidator expenseValidator;

  @Autowired
  private AccessTokenService accessTokenService;

  @PostMapping(value = "/create_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> createExpense(@RequestBody ExpenseDO expenseDo,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/v1/api/expense/create_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      //validate expense
      expenseService.createExpense(user.getId(), expenseDo, cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Create expense request for createExpense failed due to : {}",
          StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @PutMapping(value = "/update_expense/{expenseId}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateExpense(@PathVariable("expenseId") Long expenseId,
      ExpenseDO expenseDO, HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/update_expense/{expenseId}";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      expenseService.updateExpense(user.getId(), expenseDO, cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Update expense request failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @GetMapping(value = "/get_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getExpenses(HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_expenses";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      expenseService.getExpense(user.getId(), cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Get expense request  failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @GetMapping(value = "/get_monthly_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getMonthlyExpenses(@RequestParam("year") Integer year,
      @RequestParam("month") String month, HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_monthly_expenses";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      expenseService.getMonthlyExpense(user.getId(), month, year, cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Get monthly expense request  failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @GetMapping(value = "/get_yearly_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getYearlyExpense(@RequestParam("year") Integer year,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_yearly_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      expenseService.getYearlyExpense(user.getId(), year, cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Get yearly expense request  failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @GetMapping(value = "/expense_by_date", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getExpenseByDate(@RequestParam("startDate") Long date,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      expenseService.getExpenseByDate(user.getId(), date, cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Get range expense request failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @GetMapping(value = "/get_range_expense", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getRangeExpense(
      @RequestParam("startDate") Long startDate, @RequestParam("endDate") Long endDate,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_range_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      UserDO user = accessTokenService.verifyAccessToken(request);
      expenseService.getRangeExpense(user.getId(), startDate, endDate, cf);
      processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    } catch (Exception e) {
      log.error("Get range expense request failed due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }
}
