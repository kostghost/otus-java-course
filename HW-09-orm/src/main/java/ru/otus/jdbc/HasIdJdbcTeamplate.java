package ru.otus.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.model.HasIdModel;
import ru.otus.api.sessionmanager.SessionManager;

public class HasIdJdbcTeamplate<T extends HasIdModel> implements JdbcTemplate<T> {

    private static final Logger logger = LoggerFactory.getLogger(HasIdJdbcTeamplate.class);
    private final SessionManager sesionManager;
    private final DbExecutor dbExecutor;

    public HasIdJdbcTeamplate(SessionManager sessionManager, DbExecutor dbExecutor) {
        this.sesionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void create(T objectData) {

    }

    @Override
    public void update(T objectData) {

    }

    @Override
    public T load(long id, Class<T> clazz) {
        return null;
    }
}
