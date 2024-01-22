package com.profile.matcher.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static com.profile.matcher.utils.Constants.DateTimePatterns.DATE_WITH_TIME;

public final class DateHelper {

    public static Optional<String> toFormattedDateTimeString(Timestamp dateTime) {
        if (null == dateTime) {
            return Optional.empty();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_WITH_TIME);
        String formattedDateTime = formatter.format(dateTime);

        return Optional.of(formattedDateTime);
    }
}
