package com.jecrc.foundation.expense_tracker.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDo {
  private String email;
  private String password;
}
