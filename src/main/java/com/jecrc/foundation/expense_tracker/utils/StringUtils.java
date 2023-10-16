package com.jecrc.foundation.expense_tracker.utils;

public class StringUtils {
  private StringUtils() {
  }

  public static boolean isEmpty(String string) {
    if (string != null && string.isEmpty()) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(String string) {
    return string != null && !string.isEmpty();
  }

  public static boolean isNullOrEmpty(String string) {
    return string == null || string.isEmpty();
  }

  public static boolean isNotNullAndEmpty(String string) {
    return string != null && !string.isEmpty();
  }

  public static int convertToInt(String string) {
    return Integer.parseInt(string);
  }

  public static float convertToFloat(String string) {
    return Float.parseFloat(string);
  }

  public static long convertToLong(String string) {
    return Long.parseLong(string);
  }

  public static double convertToDouble(String string) {
    return Double.parseDouble(string);
  }

  public static String printStackTrace(Exception e) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Exception: ").append(e.getMessage()).append("\n\t");
    for (StackTraceElement stmt : e.getStackTrace()) {
      stringBuilder.append(stmt.toString()).append("\n\t");
    }
    return stringBuilder.toString();
  }

  public static boolean convertToBoolean(String string) {
    return Boolean.parseBoolean(string);
  }

}
