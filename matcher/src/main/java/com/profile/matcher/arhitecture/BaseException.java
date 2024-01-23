package com.profile.matcher.arhitecture;

import org.springframework.http.HttpStatus;

import static com.profile.matcher.utils.Constants.ErrorCode.DEFAULT_ERROR_CODE;

public class BaseException extends RuntimeException {

    private final String code;
    private final HttpStatus status;

    public BaseException(String message) {
        super(message);
        this.code = DEFAULT_ERROR_CODE;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = DEFAULT_ERROR_CODE;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public BaseException(String message, Throwable cause, String code, HttpStatus status) {
        super(message, cause);
        this.code = code;
        this.status = status;
    }

    public BaseException(String message, String code, HttpStatus status) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
