package com.eklanfar.library.repository;

import com.eklanfar.library.exception.database.MsSqlErrorCodesTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class IdentityScanRepository {

    private SimpleJdbcCall create;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        create = new SimpleJdbcCall(dataSource).withProcedureName("IdentityScan_Create");
    }

    public void create(final Map<String, Object> inParam) {
        create.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        create.execute(inParam);
    }
}
