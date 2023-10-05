package com.jecrc.foundation.expense_tracker.constants;

import javax.swing.plaf.PanelUI;

public class HttpResponseErrorMessage {

  private HttpResponseErrorMessage() {
  }

  public static final String ERROR_OCCURRED = "Something Went Wrong";
  public static final String INVALID_EMAIL = "Please Enter the Valid Email";
  public static final String INVALID_PASSWORD = "Please Enter the Password in range";
  public static final String INVALID_REQUEST_BODY = "Please Enter the Valid Request Body";
  public static final String AUTHORIZATION_HEADER_IS_MISSING = "Authorization Header is missing";
  public static final String INVALID_AUTHORIZATION_HEADER = "Invalid Authorization header";
  public static final String TOKEN_EXPIRED_OR_INVALID =
      "Either the token is expired or it is invalid";
  public static final String USER_WITH_MAIL_ALREADY_EXISTS = "User with mail already exists";
  public static final String USER_NOT_FOUND = "User not found";
  public static final String INCORRECT_CREDENTIALS = "Incorrect Credentials";
  public static final String EXPENSE_NOT_FOUND = "Expense not found";
  public static final String USERNAME_ALREADY_EXISTS = "Username already exists";

}
