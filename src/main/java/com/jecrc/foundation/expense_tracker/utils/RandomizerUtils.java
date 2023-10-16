package com.jecrc.foundation.expense_tracker.utils;

import java.util.Random;
import java.util.UUID;

public class RandomizerUtils {
  private static final Random random = new Random();
  private static int leftLimit = '0';
  private static int rightLimit = '9';

  public static String generateOTPString(Integer stringLength) {
    StringBuilder buffer = new StringBuilder(stringLength);
    for (int i = 0; i < stringLength; i++) {
      int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char) randomLimitedInt);
    }
    return buffer.toString();
  }

}
