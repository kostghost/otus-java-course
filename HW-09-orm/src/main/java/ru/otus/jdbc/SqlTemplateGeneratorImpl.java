package ru.otus.jdbc;

import java.util.List;
import java.util.stream.Collectors;

public class SqlTemplateGeneratorImpl implements SqlTemplateGenerator {

    // Чтобы builder приносил тут пользу нужно, чтобы названия столбцов
    // добавлялись с помощью append. Но так выглядит лучше, потому что можно воспользоваться
    // joining() и не высчитывать знаки

    @Override
    public String insert(String tableName, List<String> fieldNames) {
        var builder = new StringBuilder();

        builder.append("insert into ")
                .append(tableName)
                .append(" (")
                .append(String.join(", ", fieldNames))
                .append(") values (")
                .append(fieldNames.stream().map(key -> "?").collect(Collectors.joining(", ")))
                .append(")");

        return builder.toString();
    }

    @Override
    public String update(String tableName, List<String> fieldNames, String keyName) {
        var builder = new StringBuilder();

        builder.append("update ")
                .append(tableName)
                .append(" set ")
                .append(fieldNames.stream().map(field -> field + " = ?").collect(Collectors.joining(", ")))
                .append(" where ")
                .append(keyName)
                .append(" = ?");

        return builder.toString();
    }

    @Override
    public String select(String tableName, List<String> fieldNames, String keyName) {
        var builder = new StringBuilder();

        builder.append("select ")
                .append(String.join(", ", fieldNames))
                .append(" from ")
                .append(tableName)
                .append(" where ")
                .append(keyName)
                .append(" = ?");

        return builder.toString();
    }

}
