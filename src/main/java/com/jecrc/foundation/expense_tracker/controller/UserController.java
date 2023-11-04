package com.jecrc.foundation.expense_tracker.controller;

import com.jecrc.foundation.expense_tracker.dos.SignInDo;
import com.jecrc.foundation.expense_tracker.dos.SignUpDo;
import com.jecrc.foundation.expense_tracker.dos.UserDo;
import com.jecrc.foundation.expense_tracker.helper_service.AccessTokenService;
import com.jecrc.foundation.expense_tracker.service.UserService;
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

  private final UserService userService;

  private final UserValidator userValidator;

  private final AccessTokenService accessTokenService;

  @Autowired
  private UserController(UserService userService, UserValidator userValidator,
      AccessTokenService accessTokenService) {
    this.userService = userService;
    this.userValidator = userValidator;
    this.accessTokenService = accessTokenService;
  }

  @PostMapping(value = "/sign_up", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> signUp(@RequestBody SignUpDo signUpDo) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    String apiEndPoint = "/api/user/sign_up";
    String reqId = generateReqID();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    log.info("Request received for signUp the user");
    userValidator.validateSignUpDo(signUpDo);
    userService.signUp(signUpDo, cf);
    processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    return df;
  }

  @PostMapping(value = "/sign_in", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> signIn(@RequestBody SignInDo signInDo) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/sign_in";
    String reqId = generateReqID();
    log.info("Request received for signIn the user");
    userValidator.validateSignInDo(signInDo);
    userService.signIn(signInDo, cf);
    processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    return df;
  }

  @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> getUser(HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/get";
    String reqId = generateReqID();
    log.info("Request received for get the users profile");
    UserDo user = accessTokenService.verifyAccessToken(request);
    userService.getUser(user.getId(), cf);
    processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    return df;
  }

  @PutMapping(value = "/update_user_profile", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateUserProfile(@RequestBody UserDo userDO,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/update_user_profile";
    String reqId = generateReqID();
    log.info("Request received for updating the users profile");
    UserDo user = accessTokenService.verifyAccessToken(request);
    userService.createUserProfile(user, userDO, cf);
    processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
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
    log.info("Request received for uploading the users profile");
    UserDo user = accessTokenService.verifyAccessToken(request);
    //TODO:create service for upload user profile (integrate AWS S3 or GCP)
    processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    return df;
  }

  @PostMapping(value = "/update_transaction_limit", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<?>> updateTransactionLimit(UserDo userDO,
      HttpServletRequest request) {
    DeferredResult<ResponseEntity<?>> df = getDeferredResult();
    CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
    String apiEndPoint = "/api/user/update_transaction_limit";
    String reqId = generateReqID();
    log.info("Request received for uploading the users profile");
    UserDo user = accessTokenService.verifyAccessToken(request);
    userService.updateTransactionLimit(user.getId(), userDO.getTransactionLimit(), cf);
    processDeferredResult(df, cf, apiEndPoint, System.currentTimeMillis(), reqId);
    return df;
  }

}
