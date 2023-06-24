package com.jecrc.foundation.expense_tracker.dbos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDBO {
  private Long id;
  private String name;
  private String nickName;
  private String email;
  private String password;
  private Double transactionLimit;
  private String profileImageUrl;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}