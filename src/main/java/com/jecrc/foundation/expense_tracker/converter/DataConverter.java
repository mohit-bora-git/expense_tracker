package com.jecrc.foundation.expense_tracker.converter;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
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

  public static UserDBO convertSignUpDoToUserDbo(SignUpDO signUpDo) {
    UserDBO userDbo = new UserDBO();
    userDbo.setEmail(signUpDo.getEmail());
    userDbo.setPassword(signUpDo.getPassword());
    return userDbo;
  }

  public static ExpenseDBO convertExpenseDoToExpenseDbo(ExpenseDO expenseDO, Long id) {
    ExpenseDBO expenseDBO = new ExpenseDBO();
    expenseDBO.setTransactionAmount(expenseDO.getTransactionAmount());
    expenseDBO.setDate(expenseDO.getDate());
    expenseDBO.setExpenseType(expenseDO.getExpenseType());
    expenseDBO.setTransactionType(expenseDO.getTransactionType());
    expenseDBO.setDescription(expenseDO.getDescription());
    expenseDBO.setUserId(id);
    return expenseDBO;
  }

  public static ExpenseDO convertExpenseDboToExpenseDo(ExpenseDBO expenseDBO) {
    ExpenseDO expenseDO = new ExpenseDO();
    expenseDO.setId(expenseDBO.getId());
    expenseDO.setTransactionAmount(expenseDBO.getTransactionAmount());
    expenseDO.setDate(expenseDBO.getDate());
    expenseDO.setExpenseType(expenseDBO.getExpenseType());
    expenseDO.setTransactionType(expenseDBO.getTransactionType());
    expenseDO.setDescription(expenseDBO.getDescription());
    expenseDO.setCreatedAt(expenseDBO.getCreatedAt().getTime());
    expenseDO.setUpdatedAt(expenseDBO.getUpdatedAt().getTime());
    return expenseDO;
  }
}
