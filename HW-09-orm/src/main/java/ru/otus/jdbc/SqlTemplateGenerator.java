package ru.otus.jdbc;

import java.util.List;

public interface SqlTemplateGenerator {

    String insert(String tableName, List<String> fieldNames);

    String update(String tableName, List<String> fieldNames, String keyName);

    String select(String tableName, List<String> fieldNames, String keyName);
}
