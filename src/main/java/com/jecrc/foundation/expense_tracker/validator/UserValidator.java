package com.jecrc.foundation.expense_tracker.validator;

import com.jecrc.foundation.expense_tracker.constants.ErrorResponseCode;
import com.jecrc.foundation.expense_tracker.constants.ErrorResponseMessage;
import com.jecrc.foundation.expense_tracker.dos.ErrorResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.helper.ConfigPropertyService;
import com.jecrc.foundation.expense_tracker.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    private static final Logger logger= LoggerFactory.getLogger(UserValidator.class);

    @Autowired
    private ConfigPropertyService configPropertyService;

    public ErrorResponse validateSignUpDo(SignUpDO signUpDo){
        if(signUpDo==null || signUpDo.getEmail()==null || signUpDo.getPassword()==null){
            return new ErrorResponse(ErrorResponseCode.INVALID_REQUEST_BODY, ErrorResponseMessage.INVALID_REQUEST_BODY);
        }else if(validateEmail(signUpDo.getEmail())){
            return new ErrorResponse(ErrorResponseCode.INVALID_EMAIL,ErrorResponseMessage.INVALID_EMAIL);
        }else if(validatePassword(signUpDo.getPassword())){
            return new ErrorResponse(ErrorResponseCode.INVALID_PASSWORD,ErrorResponseMessage.INVALID_PASSWORD);
        }
        return null;
    }

    public boolean validateEmail(String email){
        String emailRegex=configPropertyService.getEmailRegex();
        return ValidationUtils.invalidate(emailRegex,email);
    }

    public boolean validatePassword(String password){
        int passwordLength=password.length();
        return passwordLength < configPropertyService.getPasswordMinLength() || passwordLength > configPropertyService.getPasswordMaxLength();
    }

    public ErrorResponse validateSignInDo(SignInDO signInDo){
        if(signInDo==null || signInDo.getEmail()==null || signInDo.getPassword()==null){
            return new ErrorResponse(ErrorResponseCode.INVALID_REQUEST_BODY,ErrorResponseMessage.INVALID_REQUEST_BODY);
        }
        return null;
    }
}
