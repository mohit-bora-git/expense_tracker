package com.jecrc.foundation.expense_tracker.dbos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExpenseDBO {
  private Long id;
  private Integer expenseType;
  private Double transactionAmount;
  private String remark;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
