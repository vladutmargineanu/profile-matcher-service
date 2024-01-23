package com.profile.matcher.exception;

import com.profile.matcher.arhitecture.BaseException;

public class UpdatePlayerDetailsException extends BaseException {

    public UpdatePlayerDetailsException(String message) {
        super(message);
    }

    public UpdatePlayerDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
