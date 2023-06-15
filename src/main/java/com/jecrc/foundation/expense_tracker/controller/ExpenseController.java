package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController("/api/expense")
public class ExpenseController {

  @PostMapping(value = "/create_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> createExpense(@RequestBody ExpenseDO expenseDo,
      HttpServletRequest request) {
    return null;
  }

  @PutMapping(value = "/update_expense", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> updateExpense(@RequestParam("expenseId") Long expenseId,
      HttpServletRequest request) {
    return null;
  }

  @GetMapping(value = "/get_expenses", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<?> getExpenses(HttpServletRequest request) {
    return null;
  }


}
