package com.jecrc.foundation.expense_tracker.exception;

public class TokenAuthorizationFailedException extends HttpStatusException{

  public TokenAuthorizationFailedException(Integer code,String message){
    super(code,message);
  }
}
