package com.eklanfar.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends HandledException {

    public NotAcceptableException(String message) {
        super(message);
    }

    public NotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }
}
