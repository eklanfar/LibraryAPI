package com.eklanfar.library.exception.database;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

public class MsSqlErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        return sqlEx.getErrorCode() == 50000 ? new DatabaseException(sqlEx.getMessage(), sqlEx) : null;
    }
}
