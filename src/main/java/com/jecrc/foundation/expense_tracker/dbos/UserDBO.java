package com.jecrc.foundation.expense_tracker.dbos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDBO {
  private Long id;
  private String name;
  private String userName;
  private String email;
  private String password;
  private String profileImageUrl;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
