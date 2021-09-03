package com.eklanfar.library.repository;

import com.eklanfar.library.exception.database.MsSqlErrorCodesTranslator;
import com.eklanfar.library.model.response.ActivityBookLoanHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ActivityRepository {

    private SimpleJdbcCall loan;
    private SimpleJdbcCall getLoanHistory;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        loan = new SimpleJdbcCall(dataSource).withProcedureName("Activity_CreateLoan");
        getLoanHistory = new SimpleJdbcCall(dataSource).withProcedureName("Activity_GetBookHistory");
    }

    public void loan(final Map<String, Object> inParam) {
        loan.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        loan.execute(inParam);
    }

    public List<ActivityBookLoanHistoryResponse> getLoanHistory(final Map<String, Object> inParam) {
        getLoanHistory.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        final Map<String, Object> map = getLoanHistory.execute(inParam);
        return (List<ActivityBookLoanHistoryResponse>) map.get("#result-set-1");
    }
}
