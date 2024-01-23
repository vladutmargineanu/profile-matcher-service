package com.profile.matcher.exception;

import com.profile.matcher.arhitecture.BaseException;
import org.springframework.http.HttpStatus;

public class FindPlayerDetailsException extends BaseException {

    public FindPlayerDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindPlayerDetailsException(String message, String code, HttpStatus status) {
        super(message, code, status);
    }
}
