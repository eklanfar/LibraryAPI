package com.eklanfar.library.repository;

import com.eklanfar.library.exception.database.MsSqlErrorCodesTranslator;
import com.eklanfar.library.model.response.UserCreateResponse;
import com.eklanfar.library.model.response.UserListResponse;
import com.eklanfar.library.model.response.UserMostLateResponse;
import com.eklanfar.library.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private SimpleJdbcCall getList;
    private SimpleJdbcCall create;
    private SimpleJdbcCall update;
    private SimpleJdbcCall getMostLate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        getList = new SimpleJdbcCall(dataSource).withProcedureName("User_GetList");
        create = new SimpleJdbcCall(dataSource).withProcedureName("User_Create");
        update = new SimpleJdbcCall(dataSource).withProcedureName("User_Update");
        getMostLate = new SimpleJdbcCall(dataSource).withProcedureName("User_GetMostLate");
    }

    public List<UserListResponse> getList() {
        getList.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        final Map<String, Object> map = getList.execute();
        return (List<UserListResponse>) map.get("#result-set-1");
    }

    public UserCreateResponse create(final Map<String, Object> inParam) {
        create.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        final Map<String, Object> map = create.execute(inParam);
        final List<UserCreateResponse> list = (List<UserCreateResponse>) map.get("#result-set-1");
        if (!list.isEmpty()) {
            return (UserCreateResponse) Helper.fromMapToObject(list.get(0), UserCreateResponse.class);
        }
        return null;
    }

    public void update(final Map<String, Object> inParam) {
        update.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        update.execute(inParam);
    }

    public UserMostLateResponse getMostLate() {
        getMostLate.getJdbcTemplate().setExceptionTranslator(new MsSqlErrorCodesTranslator());
        final Map<String, Object> map = getMostLate.execute();
        final List<UserMostLateResponse> list = (List<UserMostLateResponse>) map.get("#result-set-1");
        if (!list.isEmpty()) {
            return (UserMostLateResponse) Helper.fromMapToObject(list.get(0), UserMostLateResponse.class);
        }
        return null;
    }
}
