package com.jecrc.foundation.expense_tracker.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private DateUtils() {

    }

    public static final LocalDate getLocalDate(Long epoch) {
        return getLocalDate(new Date(epoch));
    }

    public static final LocalDate getLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static final int getDifferenceInYears(Long from, Long to) {
        return getDifferenceInYears(getLocalDate(from), getLocalDate(to));
    }

    public static final int getDifferenceInYears(LocalDate from, LocalDate to) {
        return Period.between(from, to).getYears();
    }

    public static String convertEpochToDate(Long epochToDate, String timezone) {
        Date date = new Date(epochToDate);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone(timezone));
        return format.format(date);
    }
}
