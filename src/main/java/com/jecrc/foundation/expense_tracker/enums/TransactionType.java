package com.jecrc.foundation.expense_tracker.enums;

public enum TransactionType {
  CASH(1),UPI(2),CHEQUE(3),CREDIT_CARD(4),DEBIT_CARD(5),NET_BANKING(6),OTHER(7);
  private final int type;

  TransactionType(int type) {
    this.type = type;
  }

  public int getTransactionType() {
    return this.type;
  }

  public TransactionType getTransactionType(int type) {
    for (TransactionType transactionType : TransactionType.values()) {
      if (transactionType.type == type) {
        return transactionType;
      }
    }
    return OTHER;
  }
}
