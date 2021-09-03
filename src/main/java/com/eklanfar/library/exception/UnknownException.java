package com.eklanfar.library.exception;

import com.eklanfar.library.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "InternalServerError")
public class UnknownException extends HandledException {

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(Throwable cause) {
        super(Constants.ERROR_INTERNAL_SERVER_ERROR, cause);
    }
}
