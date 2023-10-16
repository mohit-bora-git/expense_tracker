package com.jecrc.foundation.expense_tracker.enums;

public enum Months {
  JAN(1, "Jan"), FEB(2, "Feb"), MAR(3, "Mar"), APR(4, "Apr"), MAY(5, "May"), JUN(6, "Jun"), JUL(7,
      "Jul"), AUG(8, "Aug"), SEP(9, "Sep"), OCT(10, "Oct"), NOV(11, "Nov"), DEC(12, "Dec");

  private final Integer number;
  private final String name;

  Months(int number, String name) {
    this.number = number;
    this.name = name;
  }

  public Integer getNumber(){
    return this.number;
  }

  public String getName(){
    return this.name;
  }

  public static Integer getMonthNumber(String monthName) {
    return Months.getMonth(monthName).number;
  }

  public static Months getMonth(String monthName) {
    for (Months months : Months.values()) {
      if (months.name.equals(monthName)) {
        return months;
      }
    }
    return null;
  }

  public static Integer getMonthDays(Integer year, Months month) {
    switch (month) {
      case JAN:
      case MAR:
      case MAY:
      case JUL:
      case AUG:
      case OCT:
      case DEC:
        return 31;
      case APR:
      case JUN:
      case SEP:
      case NOV:
        return 30;
      default:
        return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) ? 29 : 28;
    }
  }
}
