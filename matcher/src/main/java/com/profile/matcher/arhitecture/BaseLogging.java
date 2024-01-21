package com.profile.matcher.arhitecture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public abstract class BaseLogging {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public void writeLog(String message, Object... args) {
        Marker marker = MarkerFactory.getMarker(String.valueOf(getClass()));
        LOG.info(marker, message, args);
    }
}
