package com.jecrc.foundation.expense_tracker.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpStatusException extends RuntimeException{
  private Integer code;
  private String message;

}
