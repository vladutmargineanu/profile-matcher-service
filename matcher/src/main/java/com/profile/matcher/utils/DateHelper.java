package com.profile.matcher.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

public final class DateHelper {

    public static Optional<String> toFormattedDateTimeString(Timestamp dateTime) {
        if (null == dateTime) {
            return Optional.empty();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
        String formattedDateTime = formatter.format(dateTime);

        return Optional.of(formattedDateTime);
    }
}
