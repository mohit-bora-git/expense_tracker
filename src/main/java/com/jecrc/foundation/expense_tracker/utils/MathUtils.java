package com.jecrc.foundation.expense_tracker.utils;

public class MathUtils {
    private MathUtils() {

    }

    public static Double calcPercentageInDouble(Number parts, Number whole) {
        if (whole==null || parts==null || whole.intValue()==0) {
            return null;
        }

        Double percent = (parts.doubleValue() * 100.0) / whole.doubleValue();
        return percent;
    }

    public static Float calcPercentageInFloat(Number parts, Number whole) {
        if (whole==null || parts==null || whole.intValue()==0) {
            return null;
        }

        Float percent = (parts.floatValue() * 100) / whole.floatValue();
        return percent;
    }

    public static Integer calcPercentageInInteger(Number parts, Number whole) {
        if (whole==null || parts==null || whole.intValue()==0) {
            return null;
        }

        Integer percent = (parts.intValue() * 100) / whole.intValue();
        return percent;
    }

    public static Long calcPercentageInLong(Number parts, Number whole) {
        if (whole==null || parts==null || whole.intValue()==0) {
            return null;
        }

        Long percent = (parts.longValue() * 100) / whole.longValue();
        return percent;
    }
}
