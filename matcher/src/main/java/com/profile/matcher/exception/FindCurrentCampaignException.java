package com.profile.matcher.exception;

import com.profile.matcher.arhitecture.BaseException;
import org.springframework.http.HttpStatus;

public class FindCurrentCampaignException extends BaseException {

    public FindCurrentCampaignException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindCurrentCampaignException(String message, String code, HttpStatus status) {
        super(message, code, status);
    }
}
