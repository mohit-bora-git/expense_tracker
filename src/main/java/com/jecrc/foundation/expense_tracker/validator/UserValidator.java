package com.jecrc.foundation.expense_tracker.validator;

import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.exception.ValidationException;
import com.jecrc.foundation.expense_tracker.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserValidator {

  @Autowired
  private ConfigProps config;

  //TODO:more validators needed for various field

  public void validateSignUpDo(SignUpDO signUpDo) {
    if (signUpDo == null || signUpDo.getEmail() == null || signUpDo.getPassword() == null) {
      throw new ValidationException(HttpResponseErrorCode.INVALID_REQUEST_BODY,
          HttpResponseErrorMessage.INVALID_REQUEST_BODY);
    } else if (validateEmail(signUpDo.getEmail())) {
      throw new ValidationException(HttpResponseErrorCode.INVALID_EMAIL,
          HttpResponseErrorMessage.INVALID_EMAIL);
    } else if (validatePassword(signUpDo.getPassword())) {
      throw new ValidationException(HttpResponseErrorCode.INVALID_PASSWORD,
          HttpResponseErrorMessage.INVALID_PASSWORD);
    }
  }

  public boolean validateEmail(String email) {
    String emailRegex = config.getEmailRegex();
    return ValidationUtils.invalidate(emailRegex, email);
  }

  public boolean validatePassword(String password) {
    int passwordLength = password.length();
    return passwordLength < config.getPasswordMinLength() || passwordLength > config.getPasswordMaxLength();
  }

  public void validateSignInDo(SignInDO signInDo) {
    if (signInDo == null || signInDo.getEmail() == null || signInDo.getPassword() == null) {
      throw new ValidationException(HttpResponseErrorCode.INVALID_REQUEST_BODY,
          HttpResponseErrorMessage.INVALID_REQUEST_BODY);
    }
  }

}
