package com.jecrc.foundation.expense_tracker.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDO {
  private Long id;
  private String name;
  private String username;
  private String email;
  private String profileImageUrl;
  private Double transactionLimit;
  private Long createdAt;
  private Long updatedAt;
}
