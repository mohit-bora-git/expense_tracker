package com.jecrc.foundation.expense_tracker.converter;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDO;
import com.jecrc.foundation.expense_tracker.dos.SignUpDO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;

import java.sql.Date;

public class DataConverter {
  private DataConverter() {
  }

  public static UserDO convertUserDboToUserDo(UserDBO userDbo) {
    return UserDO.builder().id(userDbo.getId()).username(userDbo.getUsername())
        .name(userDbo.getName()).email(userDbo.getEmail())
        .profileImageUrl(userDbo.getProfileImageUrl())
        .transactionLimit(userDbo.getTransactionLimit()).createdAt(userDbo.getCreatedAt().getTime())
        .updatedAt(userDbo.getUpdatedAt().getTime()).build();
  }

  public static UserDBO convertUserDoToUserDbo(UserDO userDo) {
    return UserDBO.builder().name(userDo.getName()).username(userDo.getUsername()).build();
  }

  public static UserDBO convertSignUpDoToUserDbo(SignUpDO signUpDo) {
    return UserDBO.builder().email(signUpDo.getEmail()).password(signUpDo.getPassword()).build();
  }

  public static ExpenseDBO convertExpenseDoToExpenseDbo(ExpenseDO expenseDO, Long id) {
    return ExpenseDBO.builder().expenseType(expenseDO.getExpenseType())
        .date(new Date(expenseDO.getDate())).userId(id).description(expenseDO.getDescription())
        .transactionType(expenseDO.getTransactionType())
        .transactionAmount(expenseDO.getTransactionAmount()).build();
  }

  public static ExpenseDO convertExpenseDboToExpenseDo(ExpenseDBO expenseDBO) {
    return ExpenseDO.builder().id(expenseDBO.getId())
        .transactionAmount(expenseDBO.getTransactionAmount()).date(expenseDBO.getDate().getTime())
        .expenseType(expenseDBO.getExpenseType()).transactionType(expenseDBO.getTransactionType())
        .description(expenseDBO.getDescription()).createdAt(expenseDBO.getCreatedAt().getTime())
        .updatedAt(expenseDBO.getUpdatedAt().getTime()).build();
  }
}
