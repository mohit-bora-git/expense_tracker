package com.jecrc.foundation.expense_tracker.utils;

import com.jecrc.foundation.expense_tracker.dos.UserDO;

public class EmailTemplateUtils {
  private EmailTemplateUtils() {
  }

  public static String createTransactionLimitExceedTemplate(UserDO user, Double overHeadTransaction) {
    return new StringBuilder().append("Hello")
        .append(StringUtils.isNotEmpty(user.getName()) ? user.getName() : "User")
        .append("! Greeting from Expense Tracker App.\n\n")
        .append("You have exceeded your monthly transaction limit by Rs.")
        .append(overHeadTransaction).append("\n").append("Please control your expenses.\n")
        .append("Thank You\n").append("Expense Tracker").toString();
  }
}
