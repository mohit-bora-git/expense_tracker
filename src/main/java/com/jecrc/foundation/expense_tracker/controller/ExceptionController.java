package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.exception.TokenAuthorizationFailedException;
import com.jecrc.foundation.expense_tracker.exception.ValidationException;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity<?> validationException(ValidationException e) {
    log.error("Exception Occurred due to  {}", StringUtils.printStackTrace(e));
    return ResponseEntity.ok(new ApiResponse<>(e.getCode(),e.getMessage()));
  }

  @ExceptionHandler(value = TokenAuthorizationFailedException.class)
  public ResponseEntity<?> tokenAuthorizationFailedException(TokenAuthorizationFailedException e) {
    log.error("Exception Occurred due to  {}", StringUtils.printStackTrace(e));
    return ResponseEntity.ok(new ApiResponse<>(e.getCode(),e.getMessage()));
  }
}
