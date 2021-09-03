package com.eklanfar.library.exception.database;

import org.springframework.dao.DataAccessException;

public class DatabaseException extends DataAccessException {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getRootCause() != null && super.getRootCause().getMessage() != null ? super.getRootCause().getMessage() : super.getMessage();
    }

}
