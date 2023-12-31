package com.jecrc.foundation.expense_tracker.dbos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDbo {
  private Long id;
  private Integer expenseType;
  private Double transactionAmount;
  private Integer transactionType;
  private String description;
  private Date date;
  private Long userId;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
