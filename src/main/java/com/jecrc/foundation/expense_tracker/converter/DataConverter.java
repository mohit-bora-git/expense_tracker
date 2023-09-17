package com.jecrc.foundation.expense_tracker.converter;

import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;

public class DataConverter {
  private DataConverter() {
  }

  public static UserDO convertUserDboToUserDo(UserDBO userDbo) {
    UserDO userDo = new UserDO();
    userDo.setId(userDbo.getId());
    userDo.setUsername(userDbo.getUsername());
    userDo.setName(userDbo.getName());
    userDo.setEmail(userDbo.getEmail());
    userDo.setProfileImageUrl(userDbo.getProfileImageUrl());
    userDo.setTransactionLimit(userDbo.getTransactionLimit());
    userDo.setCreatedAt(userDbo.getCreatedAt().getTime());
    userDo.setUpdatedAt(userDbo.getUpdatedAt().getTime());
    return userDo;
  }

  public static UserDBO convertUserDoToUserDbo(UserDO userDo) {
    UserDBO userDbo = new UserDBO();
    userDbo.setName(userDo.getName());
    userDbo.setUsername(userDo.getUsername());
    userDbo.setEmail(userDo.getEmail());
    userDbo.setProfileImageUrl(userDo.getProfileImageUrl());
    return userDbo;
  }

  public static UserDBO convertSignUpDoToUserDbo(SignUpDO signUpDo){
    UserDBO userDbo=new UserDBO();
    userDbo.setEmail(signUpDo.getEmail());
    userDbo.setPassword(signUpDo.getPassword());
    return userDbo;
  }
}
