package com.jecrc.foundation.expense_tracker.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
  private Long id;
  private String name;
  private String nickName;
  private String profileImageUrl;
  private Long createdAt;
  private Long updatedAt;
}