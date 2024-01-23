package com.profile.matcher.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static com.profile.matcher.utils.Constants.DateTimePatterns.DATE_WITH_TIME;

public final class DateHelper {

    public static String toFormattedDateTimeString(Timestamp dateTime) {
        if (null == dateTime) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_WITH_TIME);
        return formatter.format(dateTime);
    }
}
