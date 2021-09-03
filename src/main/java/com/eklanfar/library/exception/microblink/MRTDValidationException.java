package com.eklanfar.library.exception.microblink;

import com.eklanfar.library.exception.HandledException;

public class MRTDValidationException extends HandledException {

    public MRTDValidationException(String message) {
        super(message);
    }

    public MRTDValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
