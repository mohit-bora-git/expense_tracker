package com.jecrc.foundation.expense_tracker.utils;

import java.util.Random;
import java.util.UUID;

public class RandomizerUtils {
    private static Random random = new Random();
    private static int leftLimit = '0';
    private static int rightLimit = '9';

    /**
     * <p>
     * A static method which generates a n-digit OTP String when required.
     * </p>
     * <p>
     * {@code n} is by default set up as 6, but can be made configurable.
     * </p>
     *
     * @return n-digit OTP as a {@code String}.
     */
    public static String generateOTPString(Integer stringLength) {
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt =
                leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String getUUIDString() {
        return UUID.randomUUID().toString();
    }


}
