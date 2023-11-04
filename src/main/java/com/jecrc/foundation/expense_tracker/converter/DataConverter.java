package com.jecrc.foundation.expense_tracker.converter;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDbo;
import com.jecrc.foundation.expense_tracker.dbos.UserDbo;
import com.jecrc.foundation.expense_tracker.dos.ExpenseDo;
import com.jecrc.foundation.expense_tracker.dos.SignUpDo;
import com.jecrc.foundation.expense_tracker.dos.UserDo;

import java.sql.Date;

public class DataConverter {
  private DataConverter() {
  }

  public static UserDo convertUserDboToUserDo(UserDbo userDbo) {
    return UserDo.builder().id(userDbo.getId()).username(userDbo.getUsername())
        .name(userDbo.getName()).email(userDbo.getEmail())
        .profileImageUrl(userDbo.getProfileImageUrl())
        .transactionLimit(userDbo.getTransactionLimit()).createdAt(userDbo.getCreatedAt().getTime())
        .updatedAt(userDbo.getUpdatedAt().getTime()).build();
  }

  public static UserDbo convertUserDoToUserDbo(UserDo userDo) {
    return UserDbo.builder().name(userDo.getName()).username(userDo.getUsername()).build();
  }

  public static UserDbo convertSignUpDoToUserDbo(SignUpDo signUpDo) {
    return UserDbo.builder().email(signUpDo.getEmail()).password(signUpDo.getPassword()).build();
  }

  public static ExpenseDbo convertExpenseDoToExpenseDbo(ExpenseDo expenseDO, Long id) {
    return ExpenseDbo.builder().expenseType(expenseDO.getExpenseType())
        .date(new Date(expenseDO.getDate())).userId(id).description(expenseDO.getDescription())
        .transactionType(expenseDO.getTransactionType())
        .transactionAmount(expenseDO.getTransactionAmount()).build();
  }

  public static ExpenseDo convertExpenseDboToExpenseDo(ExpenseDbo expenseDBO) {
    return ExpenseDo.builder().id(expenseDBO.getId())
        .transactionAmount(expenseDBO.getTransactionAmount()).date(expenseDBO.getDate().getTime())
        .expenseType(expenseDBO.getExpenseType()).transactionType(expenseDBO.getTransactionType())
        .description(expenseDBO.getDescription()).createdAt(expenseDBO.getCreatedAt().getTime())
        .updatedAt(expenseDBO.getUpdatedAt().getTime()).build();
  }
}
