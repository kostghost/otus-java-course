package ru.otus.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcTypesHelper {
    public static Map<String, Object> mapRowToJavaTypes(ResultSet resultSet) throws SQLException {
        var map = new HashMap<String, Object>();

        resultSet.next();

        var metaData = resultSet.getMetaData();
        var columnCount = metaData.getColumnCount();

        for (var i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            var columnType = metaData.getColumnTypeName(i);
            Object value = getObjectWithJavaType(resultSet, i, columnType);

            map.put(columnName, value);

        }
        return map;
    }

    private static Object getObjectWithJavaType(ResultSet resultSet, int index, String typeName) throws SQLException {
        switch (typeName) {
            case "VARCHAR":
            case "LONGVARCHAR":
                return resultSet.getString(index);
            case "SMALLINT":
                return resultSet.getShort(index);
            case "INTEGER":
                return resultSet.getInt(index);
            case "BIGINT":
                return resultSet.getLong(index);
            case "REAL":
                return resultSet.getFloat(index);
            case "DOUBLE":
                return resultSet.getDouble(index);
            case "DECIMAL":
                return resultSet.getBigDecimal(index);
            // etc
        }

        throw new RuntimeException("Sorry but im forgot this type");
    }
}
