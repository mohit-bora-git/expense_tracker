package com.jecrc.foundation.expense_tracker.dos;

import lombok.Data;

@Data
public class UserDO {
  private Long id;
  private String name;
  private String userName;
  private String email;
  private String password;
  private String profileImageUrl;
  private Long createdAt;
  private Long updatedAt;
}
