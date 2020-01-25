package ru.otus.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.model.HasIdModel;
import ru.otus.api.sessionmanager.SessionManager;
import ru.otus.jdbc.sessionmanager.DatabaseSessionJdbc;
import ru.otus.reflection.ObjectMapper;

public class HasIdJdbcTeamplate<T extends HasIdModel> implements JdbcTemplate<T> {

    private static final Logger logger = LoggerFactory.getLogger(HasIdJdbcTeamplate.class);
    private final SessionManager sessionManager;
    private final DbExecutor dbExecutor;
    private final ObjectMapper objectMapper;
    private final String testCreate = "insert into user (id, name, age) values (? ,?, ?)";
    private final String testUpdate = "update user set name = ?, age = ? where id = 5";
    private final String testSelect = "select id, name, age from user where id = ?";
    private Map<String, Object> objectMap;

    public HasIdJdbcTeamplate(SessionManager sessionManager,
                              DbExecutor dbExecutor,
                              ObjectMapper objectMapper) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.objectMapper = objectMapper;
    }

    @Override
    public void create(T objectData) {
        Map<String, Object> asd = objectMapper.getObjectFieldMap(objectData);

        sessionManager.beginSession();
        try {
            DatabaseSessionJdbc session = (DatabaseSessionJdbc) sessionManager.getCurrentSession();
            dbExecutor.updateOrInsertRecord(session.getConnection(), testCreate, List.of("5", "kostyan", "25"));
            sessionManager.commitSession();
        } catch (SQLException e) {
            sessionManager.rollbackSession();
        }
    }

    @Override
    public void update(T objectData) {
        sessionManager.beginSession();
        try {
            DatabaseSessionJdbc session = (DatabaseSessionJdbc) sessionManager.getCurrentSession();
            dbExecutor.updateOrInsertRecord(session.getConnection(), testUpdate, List.of("new Kostyan", "20"));
            sessionManager.commitSession();
        } catch (SQLException e) {
            sessionManager.rollbackSession();
        }
    }

    @Override
    public T load(long id, Class<T> clazz) {
        return null;
    }

    private void insert(String query, List<Object> values) {
        sessionManager.beginSession();
        try {
            DatabaseSessionJdbc session = (DatabaseSessionJdbc) sessionManager.getCurrentSession();
            dbExecutor.updateOrInsertRecord(session.getConnection(), query, values);
            sessionManager.commitSession();
        } catch (SQLException e) {
            sessionManager.rollbackSession();
        }
    }
}
