package com.profile.matcher.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.profile.matcher.utils.Constants.DateTimePatterns.DATE_WITH_TIME;

public final class DateHelper {

    public static String toFormattedDateTimeString(Timestamp timestamp) {
        if (null == timestamp) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_WITH_TIME);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        return formatter.format(localDateTime);
    }

    public static Timestamp convertStringToTimestamp(String dateString) {
        if (null == dateString) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_WITH_TIME);
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);

        return Timestamp.valueOf(localDateTime);
    }
}
