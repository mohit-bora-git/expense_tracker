package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.dos.ExpenseDo;
import com.jecrc.foundation.expense_tracker.dos.UserDo;
import com.jecrc.foundation.expense_tracker.helper_service.AccessTokenService;
import com.jecrc.foundation.expense_tracker.service.ExpenseService;
import com.jecrc.foundation.expense_tracker.utils.DateTimeUtils;
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

  private final ExpenseService expenseService;

  private final ExpenseValidator expenseValidator;

  private final AccessTokenService accessTokenService;

  @Autowired
  private ExpenseController(ExpenseService expenseService, ExpenseValidator expenseValidator,
      AccessTokenService accessTokenService) {
    this.expenseService = expenseService;
    this.expenseValidator = expenseValidator;
    this.accessTokenService = accessTokenService;
  }

  @PostMapping(value = "/create_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> createExpense(@RequestBody ExpenseDo expenseDo,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/api/expense/create_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    //validate expense
    expenseService.createExpense(user, expenseDo, cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    return df;
  }

  @PutMapping(value = "/update_expense/{expenseId}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateExpense(@PathVariable("expenseId") Long expenseId,
      ExpenseDo expenseDO, HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/update_expense/{expenseId}";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    expenseService.updateExpense(user, expenseDO, cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    return df;
  }

  @GetMapping(value = "/get_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getExpenses(HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_expenses";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    expenseService.getExpense(user.getId(), cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    return df;
  }

  @GetMapping(value = "/get_monthly_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getMonthlyExpenses(@RequestParam("year") Integer year,
      @RequestParam("month") String month, HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_monthly_expenses";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    expenseService.getMonthlyExpense(user.getId(), month, year, cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    return df;
  }

  @GetMapping(value = "/get_yearly_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getYearlyExpense(@RequestParam("year") Integer year,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_yearly_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    expenseService.getYearlyExpense(user.getId(), year, cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    return df;
  }

  @GetMapping(value = "/expense_by_date", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getExpenseByDate(@RequestParam("date") Long date,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/get_expense";
    Long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    expenseService.getExpenseByDate(user.getId(), DateTimeUtils.getISTTimeInMillis(date), cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
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
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    UserDo user = accessTokenService.verifyAccessToken(request);
    expenseService.getRangeExpense(user.getId(), DateTimeUtils.getISTTimeInMillis(startDate),
        DateTimeUtils.getISTTimeInMillis(endDate), cf);
    processDeferredResult(df, cf, apiEndPoint, startTime, reqId);
    return df;
  }
}
