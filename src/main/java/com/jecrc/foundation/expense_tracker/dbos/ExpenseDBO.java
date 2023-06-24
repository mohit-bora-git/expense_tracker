package com.jecrc.foundation.expense_tracker.dbos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDBO {
  private Long id;
  private Integer expenseType;
  private Double transactionAmount;
  private Integer transactionType;
  private String description;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
