package com.jecrc.foundation.expense_tracker.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
  private Integer code;
  private String message;
  private T result;
}
