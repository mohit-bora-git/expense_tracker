package com.jecrc.foundation.expense_tracker.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class DateTimeUtils {
  private DateTimeUtils() {
  }

  private static final String IST = "Asia/Kolkata";

  public static LocalDateTime getCurrentDate() {
    return LocalDateTime.now(ZoneId.of(IST));
  }

  public static Long getMonthFirstDateInMillis() {
    LocalDateTime currentDate = getCurrentDate();
    LocalDateTime monthFirstDate =
        LocalDateTime.of(currentDate.getYear(), currentDate.getMonth().getValue(), 1, 0, 0);
    return getCurrentDateInMillis(monthFirstDate);
  }

  public static Long getCurrentDateInMillis(LocalDateTime localDateTime) {
    return localDateTime.atZone(ZoneId.of(IST)).toInstant().toEpochMilli();
  }

  public static Long getISTTimeInMillis(Long utcTimestampInMillis) {
    TimeZone istTimeZone = TimeZone.getTimeZone(ZoneId.of(IST));
    int istTimeOffset = istTimeZone.getRawOffset();
    istTimeOffset += 5 * 60 * 60 * 1000 + 30 * 60 * 1000;
    return utcTimestampInMillis + istTimeOffset;
  }

}
