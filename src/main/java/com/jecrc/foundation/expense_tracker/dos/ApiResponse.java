package com.jecrc.foundation.expense_tracker.dos;

import lombok.Data;

@Data
public class ApiResponse<T> {
  private Integer code;
  private String message;
  private T result;
}
