package com.eklanfar.library.exception;

import com.eklanfar.library.util.Constants;

public class ExceptionHandler {

    private ExceptionHandler() {
        throw new IllegalStateException("Cannot create instance of static class");
    }

    public static void handle(Exception e) {
        switch (e.getMessage()) {
            case Constants.ERROR_USER_NOT_FOUND:
            case Constants.ERROR_BOOK_NOT_FOUND:
            case Constants.ERROR_BOOK_COPY_NOT_FOUND:
                throw new NotFoundException(e.getMessage(), e);
            case Constants.ERROR_BOOK_COPY_ALREADY_LOANED:
                throw new NotAcceptableException(e.getMessage(), e);
            default:
                throw new UnknownException(e);
        }
    }
}
