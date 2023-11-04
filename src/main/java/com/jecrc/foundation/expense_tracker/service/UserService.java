package com.jecrc.foundation.expense_tracker.service;

import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.UserDao;
import com.jecrc.foundation.expense_tracker.dbos.UserDbo;
import com.jecrc.foundation.expense_tracker.dos.ApiResponse;
import com.jecrc.foundation.expense_tracker.dos.SignInDo;
import com.jecrc.foundation.expense_tracker.dos.SignUpDo;
import com.jecrc.foundation.expense_tracker.dos.UserDo;
import com.jecrc.foundation.expense_tracker.encryptor.Encryptor;
import com.jecrc.foundation.expense_tracker.helper_service.AccessTokenService;
import com.jecrc.foundation.expense_tracker.helper_service.ImageService;
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


  private final UserDao userDAO;
  private final Encryptor encryptor;
  private final AccessTokenService accessTokenService;
  private final ConfigProps configProps;
  private final ImageService imageService;

  @Autowired
  private UserService(UserDao userDao, Encryptor encryptor, AccessTokenService accessTokenService,
      ConfigProps configProps, ImageService imageService) {
    this.userDAO = userDao;
    this.encryptor = encryptor;
    this.imageService = imageService;
    this.configProps = configProps;
    this.accessTokenService = accessTokenService;
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void signUp(SignUpDo signUpDo, CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      UserDbo existingUser = userDAO.findUserByEmail(signUpDo.getEmail());
      if (!Objects.isNull(existingUser)) {
        log.info("User already exists with given userEmail");
        responseFuture.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseCode.USER_WITH_MAIL_ALREADY_EXISTS,
                HttpResponseMessage.USER_WITH_MAIL_ALREADY_EXISTS)));
        return;
      }
      signUpDo.setPassword(encryptor.getEncryptedPassword(signUpDo.getPassword()));
      userDAO.save(DataConverter.convertSignUpDoToUserDbo(signUpDo));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpStatus.OK.value(), HttpResponseMessage.USER_SUCCESSFULLY_SAVED)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void signIn(SignInDo signInDo, CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      UserDbo existingUser = userDAO.findUserByEmail(signInDo.getEmail());
      if (Objects.isNull(existingUser)) {
        log.info("User does not exists with given userEmail");
        responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseCode.USER_NOT_FOUND,
            HttpResponseMessage.USER_NOT_FOUND)));
        return;
      }
      if (!encryptor.checkEncryptedPassword(signInDo.getPassword(), existingUser.getPassword())) {
        log.info("User password does not matched");
        responseFuture.complete(ResponseEntity.ok(
            new ApiResponse<>(HttpResponseCode.INCORRECT_CREDENTIALS,
                HttpResponseMessage.INCORRECT_CREDENTIALS)));
        return;
      }
      signInDo.setAccessToken(accessTokenService.generateToken(existingUser.getId()));
      responseFuture.complete(
          ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), signInDo)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void getUser(Long userId, CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      UserDbo existingUser = userDAO.findById(userId);
      if (Objects.isNull(existingUser)) {
        log.info("User does not exists with given userEmail");
        responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpResponseCode.USER_NOT_FOUND,
            HttpResponseMessage.USER_NOT_FOUND)));
        return;
      }
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          DataConverter.convertUserDboToUserDo(existingUser))));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void createUserProfile(UserDo existingUser, UserDo userDO,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      String existingUsername = existingUser.getUsername();
      if (existingUsername != null && !existingUsername.equals(userDO.getUsername())) {
        UserDbo user = userDAO.findUserByUsername(userDO.getUsername());
        if (!Objects.isNull(user)) {
          log.info("Username already exists");
          responseFuture.complete(ResponseEntity.ok(
              new ApiResponse<>(HttpResponseCode.USERNAME_ALREADY_EXISTS,
                  HttpResponseMessage.USERNAME_ALREADY_EXISTS)));
          return;
        }
      }
      userDAO.update(DataConverter.convertUserDoToUserDbo(userDO));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpStatus.OK.value(), HttpResponseMessage.USER_SUCCESSFULLY_UPDATED)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void uploadImage(Long userId, MultipartFile profileImage,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      String imageName = configProps.getProfileImagePrefixName() + "_" + userId;
      imageService.uploadImageToS3Bucket(imageName, profileImage);
      userDAO.updateProfile(userId, configProps.getAwsS3Host() + imageName);
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          HttpResponseMessage.USER_SUCCESSFULLY_UPLOADED_PROFILE_PIC)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }

  @Async(AsyncConfig.API_EXECUTOR)
  public void updateTransactionLimit(Long userId, Double transactionLimit,
      CompletableFuture<ResponseEntity<?>> responseFuture) {
    try {
      userDAO.updateTransactionLimit(userId, transactionLimit);
      responseFuture.complete(ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
          HttpResponseMessage.TRANSACTION_LIMIT_SUCCESSFULLY_UPDATED)));
    } catch (Exception e) {
      log.error("Exception Occurred :{}", StringUtils.printStackTrace(e));
      responseFuture.complete(ResponseEntity.ok(
          new ApiResponse<>(HttpResponseCode.ERROR_OCCURRED, HttpResponseMessage.ERROR_OCCURRED)));
    }
  }
}
