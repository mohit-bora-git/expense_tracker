package com.jecrc.foundation.expense_tracker.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDO {
  private Long id;
  private Integer expenseType;
  private Double transactionAmount;
  private Integer transactionType;
  private String description;
  private Date date;
  private Long createdAt;
  private Long updatedAt;
}
