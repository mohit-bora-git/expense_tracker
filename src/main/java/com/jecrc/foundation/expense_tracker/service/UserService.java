package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dao.UserDAO;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserService {

  @Autowired
  private UserDAO userDao;

  @Async("asyncExecutor")
  public void signUp(SignUpDO signUpDo, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void signIn(SignInDO signInDo, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void getUser(Long userId, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void uploadImage(Long userId, MultipartFile profileImage,
      CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }

  @Async("asyncExecutor")
  public void updateTransactionLimit(Long userId, Double transactionLimit,
      CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED, null)));
    }
  }
}
