package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.service.ExpenseService;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController extends BaseController {

  @Autowired
  private ExpenseService expenseService;

  @PostMapping(value = "/create_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> createExpense(@RequestBody ExpenseDO expenseDo,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> deferredResult = getDeferredResult();
    String apiEndPoint = "/v1/api/expense/create_expense";
    long startTime = System.currentTimeMillis();
    String reqId = generateReqID();
    try {

    } catch (Exception e) {
      logger.info("Create expense request for createExpense failed due to : {}",
          StringUtils.printStackTrace(e));

    }
    return null;
  }

  @PutMapping(value = "/update_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> updateExpense(@RequestParam("expenseId") Long expenseId,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> deferredResult = getDeferredResult();

    return null;
  }

  @GetMapping(value = "/get_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> getExpenses(HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> deferredResult = getDeferredResult();

    return null;
  }


}
