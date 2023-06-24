package com.jecrc.foundation.expense_tracker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
  private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

  /**
   * @param timeUnit The unit of time required.
   * @return Get current time of the day of the time units given.
   */
  public static Long getCurrentTime(TimeUnit timeUnit) {
    long localTime = LocalTime.now().toNanoOfDay();
    switch (timeUnit) {
      case MICROSECONDS:
        return localTime / 1_000L;
      case MILLISECONDS:
        return localTime / 1_000_000L;
      case SECONDS:
        return localTime / 1_000_000_000L;
      default:
        return localTime;
    }
  }

  /**
   * @return The current time of the day in milliseconds.
   */
  public static Long getCurrentTime() {
    return getCurrentTime(TimeUnit.MILLISECONDS);
  }

  /**
   * @param milliSeconds Given time in milliseconds.
   * @return Time converted to nanoseconds (precision is lost due to scale).
   */
  public static Long milliToNano(Long milliSeconds) {
    return milliSeconds * 1_000_000L;
  }

  /**
   * @param nanoSeconds Time given in nanoseconds.
   * @return Time approximate converted to milliseconds.
   */
  public static Long nanoToMilli(Long nanoSeconds) {
    return nanoSeconds / 1_000_000L;
  }

  public static Long convertTimestampToEpoch(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.getTime();
  }

  public static Long getGMTTime() {
    return Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();
  }

  public static String convertTimeIntoString(Long timestamp) {
    try {
      String convertedTime = "";
      Integer time;
      Long second = 1000L;
      Long minute = 60000L;
      Long hour = 3600000L;
      Long day = 86400000L;
      // Long month = 2592000000L;
      if (timestamp < second) {
        convertedTime = "now";
      } else if (timestamp < minute) {
        time = (int) (timestamp / second);
        convertedTime = String.format("%s second(s) ago", time);
      } else if (timestamp < hour) {
        time = (int) (timestamp / minute);
        convertedTime = String.format("%s minute(s) ago", time);
      } else if (timestamp < day) {
        time = (int) (timestamp / hour);
        convertedTime = String.format("%s hour(s) ago", time);
      } else {
        time = (int) (timestamp / day);
        convertedTime = String.format("%s day(s) ago", time);
      }
      return convertedTime;
    } catch (Exception e) {
      logger.error("Exception occurred while converting time due to {}",
          StringUtils.printStackTrace(e));
    }
    return null;
  }

}
