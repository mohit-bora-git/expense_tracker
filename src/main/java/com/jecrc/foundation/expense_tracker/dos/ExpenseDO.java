package com.jecrc.foundation.expense_tracker.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDO {
  private Long id;
  private Integer expenseType;
  private Double transactionAmount;
  private Integer transactionType;
  private String remark;
  private Long createdAt;
  private Long updatedAt;
}
