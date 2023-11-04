package com.jecrc.foundation.expense_tracker.dbos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDbo {
  private Long id;
  private String name;
  private String username;
  private String email;
  private String password;
  private Double transactionLimit;
  private String profileImageUrl;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
