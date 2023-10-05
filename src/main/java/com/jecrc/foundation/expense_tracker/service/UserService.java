package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.constants.HttpSuccessMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.UserDAO;
import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;
import com.jecrc.foundation.expense_tracker.encryptor.Encryptor;
import com.jecrc.foundation.expense_tracker.helper_service.AccessTokenService;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserService {

  @Autowired
  private UserDAO userDAO;

  @Autowired
  private Encryptor encryptor;

  @Autowired
  private AccessTokenService accessTokenService;

  @Async("asyncExecutor")
  public void signUp(SignUpDO signUpDo, CompletableFuture<ResponseEntity<?>> response) {
    try {
      UserDBO existingUser = userDAO.findUserByEmail(signUpDo.getEmail());
      if (!Objects.isNull(existingUser)) {
        log.info("User already exists with given userEmail");
        response.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseErrorCode.USER_WITH_MAIL_ALREADY_EXISTS,
                HttpResponseErrorMessage.USER_WITH_MAIL_ALREADY_EXISTS)));
        return;
      }
      String encryptedPassword = encryptor.getEncryptedPassword(signUpDo.getPassword());
      signUpDo.setPassword(encryptedPassword);
      UserDBO userDBO = DataConverter.convertSignUpDoToUserDbo(signUpDo);
      userDAO.save(userDBO);
      response.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.USER_SUCCESSFULLY_SAVED)));
    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void signIn(SignInDO signInDo, CompletableFuture<ResponseEntity<?>> response) {
    try {
      UserDBO existingUser = userDAO.findUserByEmail(signInDo.getEmail());
      if (Objects.isNull(existingUser)) {
        log.info("User does not exists with given userEmail");
        response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.USER_NOT_FOUND,
            HttpResponseErrorMessage.USER_NOT_FOUND)));
        return;
      }
      if (!encryptor.checkEncryptedPassword(signInDo.getPassword(), existingUser.getPassword())) {
        log.info("User password does not matched");
        response.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseErrorCode.INCORRECT_CREDENTIALS,
                HttpResponseErrorMessage.INCORRECT_CREDENTIALS)));
        return;
      }
      String token = accessTokenService.generateToken(existingUser.getId());
      signInDo.setAccessToken(token);
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), signInDo)));
    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void getUser(Long userId, CompletableFuture<ResponseEntity<?>> response) {
    try {
      UserDBO existingUser = userDAO.findById(userId);
      if (Objects.isNull(existingUser)) {
        log.info("User does not exists with given userEmail");
        response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.USER_NOT_FOUND,
            HttpResponseErrorMessage.USER_NOT_FOUND)));
        return;
      }
      UserDO userDO = DataConverter.convertUserDboToUserDo(existingUser);
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), userDO)));
    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  public void createUserProfile(UserDO existingUser, UserDO userDO,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      String existingUsername = existingUser.getUsername();
      if (existingUsername != null && !existingUsername.equals(userDO.getUsername())) {
        UserDBO user = userDAO.findUserByUsername(userDO.getUsername());
        if (!Objects.isNull(user)) {
          log.info("Username already exists");
          response.complete(ResponseEntity.ok(
              new ApiResponse<>(HttpResponseErrorCode.USERNAME_ALREADY_EXISTS,
                  HttpResponseErrorMessage.USERNAME_ALREADY_EXISTS)));
          return;
        }
      }
      userDAO.update(DataConverter.convertUserDoToUserDbo(userDO));
      response.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.USER_SUCCESSFULLY_UPDATED)));
    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void uploadImage(Long userId, MultipartFile profileImage,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      //TODO:to be implemented
    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }

  @Async("asyncExecutor")
  public void updateTransactionLimit(Long userId, Double transactionLimit,
      CompletableFuture<ResponseEntity<?>> response) {
    try {
      //TODO:to be implemented
    } catch (Exception e) {
      log.error("Exception occurred due to : {}", StringUtils.printStackTrace(e));
      response.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseErrorCode.ERROR_OCCURRED,
          HttpResponseErrorMessage.ERROR_OCCURRED)));
    }
  }
}
