package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;
import com.jecrc.foundation.expense_tracker.helper_service.AccessTokenService;
import com.jecrc.foundation.expense_tracker.service.UserService;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import com.jecrc.foundation.expense_tracker.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController extends BaseController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserValidator userValidator;

  @Autowired
  private AccessTokenService accessTokenService;

  @PostMapping(value = "/sign_up", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> signUp(@RequestBody SignUpDO signUpDo) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/api/user/sign_up";
    String reqId = generateReqID();
    try {
      CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
      log.info("Request received for signUp the user");
      userValidator.validateSignUpDo(signUpDo);
      userService.signUp(signUpDo, cf);
      processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    } catch (Exception e) {
      log.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @PostMapping(value = "/sign_in", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> signIn(@RequestBody SignInDO signInDo) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/sign_in";
    String reqId = generateReqID();
    try {
      log.info("Request received for signIn the user");
      userValidator.validateSignInDo(signInDo);
      userService.signIn(signInDo, cf);
      processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    } catch (Exception e) {
      log.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getUser(HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/get";
    String reqId = generateReqID();
    try {
      log.info("Request received for get the users profile");
      UserDO user = accessTokenService.verifyAccessToken(request);
      userService.getUser(user.getId(), cf);
      processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    } catch (Exception e) {
      log.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @PutMapping(value = "/update_user_profile", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateUserProfile(@RequestBody UserDO userDO,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/update_user_profile";
    String reqId = generateReqID();
    try {
      log.info("Request received for updating the users profile");
      UserDO user = accessTokenService.verifyAccessToken(request);
      userService.createUserProfile(user, userDO, cf);
      processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    } catch (Exception e) {
      log.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @PostMapping(value = "/upload_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> uploadUserProfileImage(
      @RequestBody MultipartFile profileImage, HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/upload_image";
    String reqId = generateReqID();
    try {
      log.info("Request received for uploading the users profile");
      UserDO user = accessTokenService.verifyAccessToken(request);
      //TODO:create service for upload user profile (integrate AWS S3 or GCP)
      processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    } catch (Exception e) {
      log.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

  @PostMapping(value = "/update_transaction_limit", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateTransactionLimit(UserDO userDO,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/update_transaction_limit";
    String reqId = generateReqID();
    try {
      log.info("Request received for uploading the users profile");
      UserDO user = accessTokenService.verifyAccessToken(request);
      userService.updateTransactionLimit(user.getId(), userDO.getTransactionLimit(), cf);
      processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    } catch (Exception e) {
      log.error("Error occurred due to : {}", StringUtils.printStackTrace(e));
      df.setResult(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
    return df;
  }

}
