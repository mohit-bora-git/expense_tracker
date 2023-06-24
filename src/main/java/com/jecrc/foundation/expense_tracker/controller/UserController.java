package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.constants.ErrorResponseCode;
import com.jecrc.foundation.expense_tracker.constants.ErrorResponseMessage;
import com.jecrc.foundation.expense_tracker.dos.ErrorResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.service.UserService;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import com.jecrc.foundation.expense_tracker.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping(value = "/sign_up",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<?>> signUp(@RequestBody SignUpDO signUpDo){
        DeferredResult<ResponseEntity<?>> df=getDeferredResult();
        CompletableFuture<ResponseEntity<?>> cf=new CompletableFuture<>();
        String apiEndPoint="/api/user/sign_up";
        try{
            String reqId=generateReqID();
            logger.info("Request received for signUp the user");
            ErrorResponse validateResponse=userValidator.validateSignUpDo(signUpDo);
            if(validateResponse!=null){
                df.setResult(ResponseEntity.ok(validateResponse));
            }else{

            }
            processDeferredResult(df,cf,apiEndPoint,System.currentTimeMillis(),reqId);
        }catch (Exception e){
            logger.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
            df.setResult(ResponseEntity.ok(new ErrorResponse(ErrorResponseCode.ERROR_OCCURRED, ErrorResponseMessage.ERROR_OCCURRED)));
        }
        return df;
    }

    @PostMapping(value = "/sign_in",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<?>> signIn(@RequestBody SignInDO signInDo){
        DeferredResult<ResponseEntity<?>> df=getDeferredResult();
        CompletableFuture<ResponseEntity<?>> cf=new CompletableFuture<>();
        String apiEndPoint="/api/user/sign_up";
        try{
            String reqId=generateReqID();
            logger.info("Request received for signIn the user");
            ErrorResponse validateResponse=userValidator.validateSignInDo(signInDo);
            if(validateResponse!=null){
                df.setResult(ResponseEntity.ok(validateResponse));
            }else{

            }
            processDeferredResult(df,cf,apiEndPoint,System.currentTimeMillis(),reqId);
        }catch (Exception e){
            logger.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
            df.setResult(ResponseEntity.ok(new ErrorResponse(ErrorResponseCode.ERROR_OCCURRED, ErrorResponseMessage.ERROR_OCCURRED)));
        }
        return df;
    }

}