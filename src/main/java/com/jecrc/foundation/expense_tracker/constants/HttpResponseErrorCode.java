package com.jecrc.foundation.expense_tracker.constants;

public class HttpResponseErrorCode {
  private HttpResponseErrorCode() {
  }

  public static final Integer ERROR_OCCURRED = 1001;
  public static final Integer INVALID_EMAIL = 1002;
  public static final Integer INVALID_PASSWORD = 1003;
  public static final Integer INVALID_REQUEST_BODY = 1004;
  public static final Integer AUTHORIZATION_HEADER_IS_MISSING = 1005;
  public static final Integer INVALID_AUTHORIZATION_HEADER = 1006;
  public static final Integer TOKEN_EXPIRED_OR_INVALID = 1007;

}
