package com.profile.matcher.exception;

import com.profile.matcher.arhitecture.BaseException;

public class NotAllowedException extends BaseException {

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
