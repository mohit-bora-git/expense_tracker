package com.jecrc.foundation.expense_tracker.exception;

public class ValidationException extends RuntimeException{
  private final Integer code;
  private final String message;

  public ValidationException(Integer code, String message){
    this.code=code;
    this.message=message;
  }

  public Integer getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
