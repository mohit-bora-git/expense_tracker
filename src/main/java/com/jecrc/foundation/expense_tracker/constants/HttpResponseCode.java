package com.jecrc.foundation.expense_tracker.constants;


public class HttpResponseCode {
  private HttpResponseCode() {
  }

  public static final Integer ERROR_OCCURRED = 1001;
  public static final Integer INVALID_EMAIL = 1002;
  public static final Integer INVALID_PASSWORD = 1003;
  public static final Integer INVALID_REQUEST_BODY = 1004;
  public static final Integer AUTHORIZATION_HEADER_IS_MISSING = 1005;
  public static final Integer INVALID_AUTHORIZATION_HEADER = 1006;
  public static final Integer TOKEN_EXPIRED_OR_INVALID = 1007;
  public static final Integer USER_WITH_MAIL_ALREADY_EXISTS = 1008;
  public static final Integer USER_NOT_FOUND = 1009;
  public static final Integer INCORRECT_CREDENTIALS = 1010;
  public static final Integer EXPENSE_NOT_FOUND = 1011;
  public static final Integer USERNAME_ALREADY_EXISTS = 1012;


}
