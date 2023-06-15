package com.jecrc.foundation.expense_tracker.dos;

import lombok.Data;

@Data
public class ExpenseDO {
  private Long id;
  private Integer expenseType;
  private Double transactionAmount;
  private String remark;
  private Long createdAt;
  private Long updatedAt;
}
