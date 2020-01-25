package ru.otus.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.annotations.Id;
import ru.otus.api.model.HasIdModel;
import ru.otus.api.sessionmanager.SessionManager;
import ru.otus.jdbc.sessionmanager.DatabaseSessionJdbc;
import ru.otus.reflection.ObjectMapper;

public class HasIdJdbcTemplate<T extends HasIdModel> implements JdbcTemplate<T> {

    private static final Logger logger = LoggerFactory.getLogger(HasIdJdbcTemplate.class);
    private final SessionManager sessionManager;
    private final DbExecutor<T> dbExecutor;
    private final ObjectMapper objectMapper;
    private final SqlTemplateGenerator sqlTemplateGenerator;

    public HasIdJdbcTemplate(SessionManager sessionManager,
                             DbExecutor<T> dbExecutor,
                             ObjectMapper objectMapper,
                             SqlTemplateGenerator sqlTemplateGenerator) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.objectMapper = objectMapper;
        this.sqlTemplateGenerator = sqlTemplateGenerator;
    }

    @Override
    public void create(T objectData) {
        Map<String, Object> asd = objectMapper.getObjectFieldMap(objectData);

        sessionManager.beginSession();
        try {
            DatabaseSessionJdbc session = (DatabaseSessionJdbc) sessionManager.getCurrentSession();

            String tableName = objectMapper.getObjectClassName(objectData.getClass());
            var fields = objectMapper.getObjectFieldMap(objectData);
            var fieldNames = new ArrayList<>(fields.keySet());
            var fieldValues = fields.values().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
            var queryTemplate = sqlTemplateGenerator.insert(tableName, fieldNames);

            dbExecutor.updateOrInsertRecord(session.getConnection(), queryTemplate, fieldValues);

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

            String tableName = objectMapper.getObjectClassName(objectData.getClass());

            var fields = objectMapper.getObjectFieldMap(objectData);
            // todo добавить exception
            var keyName = objectMapper.getFieldNamesWithAnnotaion(objectData, Id.class).get(0);

            String queryTemplate = sqlTemplateGenerator.update(tableName,
                    getColsForUpdate(fields, keyName),
                    keyName);

            dbExecutor.updateOrInsertRecord(session.getConnection(),
                    queryTemplate,
                    getParamsForUpdate(fields, keyName));

            sessionManager.commitSession();
        } catch (SQLException e) {
            sessionManager.rollbackSession();
        }
    }

    // Удаляем ключ т.к. его не должно быть в запросе
    private List<String> getColsForUpdate(LinkedHashMap<String, Object> fields, String keyName) {
        return fields.keySet().stream()
                .filter(key -> !key.equalsIgnoreCase(keyName))
                .collect(Collectors.toList());
    }

    // Перемещаем значение ключа в конец т.к. он вставляется в последний ? после where
    private List<String> getParamsForUpdate(LinkedHashMap<String, Object> fields, String keyName) {
        var keyValue = fields.get(keyName);
        var values = fields.keySet().stream()
                .filter(key -> !key.equalsIgnoreCase(keyName))
                .map(key -> fields.get(key).toString())
                .collect(Collectors.toList());
        values.add(keyValue.toString());

        return values;
    }

    @Override
    public T load(long id, Class<T> clazz) {
//        sessionManager.beginSession();
//        try {
//            DatabaseSessionJdbc session = (DatabaseSessionJdbc) sessionManager.getCurrentSession();
//
//            String tableName = objectMapper.getObjectClassName(clazz);
//
//            var sql = sqlTemplateGenerator.select(tableName, )
//
//            dbExecutor.selectRecord(session.getConnection(), )
//
//            sessionManager.commitSession();
//        } catch (SQLException e) {
//            sessionManager.rollbackSession();
//        }
        return null;
    }


}
