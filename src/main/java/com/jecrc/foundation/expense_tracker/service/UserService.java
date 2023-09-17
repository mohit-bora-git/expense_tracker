package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.dao.UserDAO;
import com.jecrc.foundation.expense_tracker.dos.ErrorResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserDAO userDao;

  @Async("asyncExecutor")
  public void signUp(SignUpDO signUpDo, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      logger.info("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ErrorResponse(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void signIn(SignInDO signInDo, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      logger.info("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ErrorResponse(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getUser(Long userId, CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      logger.info("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ErrorResponse(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void uploadImage(Long userId, MultipartFile profileImage,
      CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      logger.info("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ErrorResponse(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void updateTransactionLimit(Long userId, Double transactionLimit,
      CompletableFuture<ResponseEntity<?>> response) {
    try {

    } catch (Exception e) {
      logger.info("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ErrorResponse(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }
}
