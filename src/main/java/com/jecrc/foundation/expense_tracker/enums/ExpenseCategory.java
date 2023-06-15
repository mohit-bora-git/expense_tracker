package com.jecrc.foundation.expense_tracker.enums;

public enum ExpenseCategory {
  HOUSING(1), UTILITIES(2), TRANSPORTATION(3), FOOD(4), PERSONAL_CARE(5), HEALTH_CARE(6), EDUCATION(
      7), ENTERTAINMENT(8), TRAVEL(9), DEBT_PAYMENT(10), INSURANCE(11), SAVING_INVESTMENT(
      12), CLOTHING_ACCESSORIES(13), GIFTS_DONATION(14), OTHERS(15);

  private final int expenseType;

  ExpenseCategory(int expenseType) {
    this.expenseType = expenseType;
  }

  public int getExpenseType() {
    return this.expenseType;
  }

  public ExpenseCategory getExpenseCategory(int type) {
    for (ExpenseCategory expenseCategory : ExpenseCategory.values()) {
      if (expenseCategory.expenseType == type) {
        return expenseCategory;
      }
    }
    return null;
  }
}
