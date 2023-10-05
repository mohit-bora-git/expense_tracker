package com.jecrc.foundation.expense_tracker.dos;

import lombok.Data;

@Data
public class ApiResponse<T> {
  private Integer code;
  private String message;
  private T result;

  public ApiResponse() {
  }

  public ApiResponse(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public ApiResponse(Integer code, T result, String message) {
    this.code = code;
    this.message = message;
    this.result = result;
  }

  public ApiResponse(Integer code, T result) {
    this.code = code;
    this.result = result;
  }
}
