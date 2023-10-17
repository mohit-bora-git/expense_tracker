package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.config.ConfigProps;
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
import com.jecrc.foundation.expense_tracker.helper_service.ImageService;
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

  @Autowired
  private ConfigProps configProps;

  @Autowired
  private ImageService imageService;

  @Async(AsyncConfig.API_EXECUTOR)
  public void signUp(SignUpDO signUpDo, CompletableFuture<ResponseEntity<?>> responseFuture) {
    UserDBO existingUser = userDAO.findUserByEmail(signUpDo.getEmail());
    if (!Objects.isNull(existingUser)) {
      log.info("User already exists with given userEmail");
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseErrorCode.USER_WITH_MAIL_ALREADY_EXISTS,
              HttpResponseErrorMessage.USER_WITH_MAIL_ALREADY_EXISTS)));
      return;
    }
    signUpDo.setPassword(encryptor.getEncryptedPassword(signUpDo.getPassword()));
    userDAO.save(DataConverter.convertSignUpDoToUserDbo(signUpDo));
    responseFuture.complete(ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.USER_SUCCESSFULLY_SAVED)));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void signIn(SignInDO signInDo, CompletableFuture<ResponseEntity<?>> responseFuture) {
    UserDBO existingUser = userDAO.findUserByEmail(signInDo.getEmail());
    if (Objects.isNull(existingUser)) {
      log.info("User does not exists with given userEmail");
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseErrorCode.USER_NOT_FOUND,
              HttpResponseErrorMessage.USER_NOT_FOUND)));
      return;
    }
    if (!encryptor.checkEncryptedPassword(signInDo.getPassword(), existingUser.getPassword())) {
      log.info("User password does not matched");
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseErrorCode.INCORRECT_CREDENTIALS,
              HttpResponseErrorMessage.INCORRECT_CREDENTIALS)));
      return;
    }
    signInDo.setAccessToken(accessTokenService.generateToken(existingUser.getId()));
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), signInDo)));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getUser(Long userId, CompletableFuture<ResponseEntity<?>> responseFuture) {
    UserDBO existingUser = userDAO.findById(userId);
    if (Objects.isNull(existingUser)) {
      log.info("User does not exists with given userEmail");
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseErrorCode.USER_NOT_FOUND,
              HttpResponseErrorMessage.USER_NOT_FOUND)));
      return;
    }
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        DataConverter.convertUserDboToUserDo(existingUser))));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void createUserProfile(UserDO existingUser, UserDO userDO,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    String existingUsername = existingUser.getUsername();
    if (existingUsername != null && !existingUsername.equals(userDO.getUsername())) {
      UserDBO user = userDAO.findUserByUsername(userDO.getUsername());
      if (!Objects.isNull(user)) {
        log.info("Username already exists");
        responseFuture.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseErrorCode.USERNAME_ALREADY_EXISTS,
                HttpResponseErrorMessage.USERNAME_ALREADY_EXISTS)));
        return;
      }
    }
    userDAO.update(DataConverter.convertUserDoToUserDbo(userDO));
    responseFuture.complete(ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), HttpSuccessMessage.USER_SUCCESSFULLY_UPDATED)));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void uploadImage(Long userId, MultipartFile profileImage,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    String imageName = configProps.getProfileImagePrefixName() + "_" + userId;
    imageService.uploadImageToS3Bucket(imageName, profileImage);
    userDAO.updateProfile(userId, configProps.getAwsS3Host() + imageName);
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        HttpSuccessMessage.USER_SUCCESSFULLY_UPLOADED_PROFILE_PIC)));
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void updateTransactionLimit(Long userId, Double transactionLimit,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    userDAO.updateTransactionLimit(userId, transactionLimit);
    responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
        HttpSuccessMessage.TRANSACTION_LIMIT_SUCCESSFULLY_UPDATED)));
  }
}
