package com.jecrc.foundation.expense_tracker.validator;

import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dos.ErrorResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
  private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

  @Autowired
  private ConfigProps config;

  public ErrorResponse validateSignUpDo(SignUpDO signUpDo) {
    if (signUpDo == null || signUpDo.getEmail() == null || signUpDo.getPassword() == null) {
      return new ErrorResponse(HttpResponseErrorCode.INVALID_REQUEST_BODY,
          HttpResponseErrorMessage.INVALID_REQUEST_BODY);
    } else if (validateEmail(signUpDo.getEmail())) {
      return new ErrorResponse(HttpResponseErrorCode.INVALID_EMAIL, HttpResponseErrorMessage.INVALID_EMAIL);
    } else if (validatePassword(signUpDo.getPassword())) {
      return new ErrorResponse(HttpResponseErrorCode.INVALID_PASSWORD,
          HttpResponseErrorMessage.INVALID_PASSWORD);
    }
    return null;
  }

  public boolean validateEmail(String email) {
    String emailRegex = config.getEmailRegex();
    return ValidationUtils.invalidate(emailRegex, email);
  }

  public boolean validatePassword(String password) {
    int passwordLength = password.length();
    return passwordLength < config.getPasswordMinLength() || passwordLength > config.getPasswordMaxLength();
  }

  public ErrorResponse validateSignInDo(SignInDO signInDo) {
    if (signInDo == null || signInDo.getUsernameOrEmail() == null || signInDo.getPassword() == null) {
      return new ErrorResponse(HttpResponseErrorCode.INVALID_REQUEST_BODY,
          HttpResponseErrorMessage.INVALID_REQUEST_BODY);
    }
    return null;
  }
}
