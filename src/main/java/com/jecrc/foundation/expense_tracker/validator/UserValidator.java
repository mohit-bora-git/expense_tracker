package com.jecrc.foundation.expense_tracker.validator;

import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseMessage;
import com.jecrc.foundation.expense_tracker.dos.SignInDo;
import com.jecrc.foundation.expense_tracker.dos.SignUpDo;
import com.jecrc.foundation.expense_tracker.exception.ValidationException;
import com.jecrc.foundation.expense_tracker.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserValidator {

  @Autowired
  private ConfigProps config;

  //TODO:more validators needed for various field

  public void validateSignUpDo(SignUpDo signUpDo) {
    if (signUpDo == null || signUpDo.getEmail() == null || signUpDo.getPassword() == null) {
      throw new ValidationException(HttpResponseCode.INVALID_REQUEST_BODY,
          HttpResponseMessage.INVALID_REQUEST_BODY);
    } else if (validateEmail(signUpDo.getEmail())) {
      throw new ValidationException(HttpResponseCode.INVALID_EMAIL,
          HttpResponseMessage.INVALID_EMAIL);
    } else if (validatePassword(signUpDo.getPassword())) {
      throw new ValidationException(HttpResponseCode.INVALID_PASSWORD,
          HttpResponseMessage.INVALID_PASSWORD);
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

  public void validateSignInDo(SignInDo signInDo) {
    if (signInDo == null || signInDo.getEmail() == null || signInDo.getPassword() == null) {
      throw new ValidationException(HttpResponseCode.INVALID_REQUEST_BODY,
          HttpResponseMessage.INVALID_REQUEST_BODY);
    }
  }

}
