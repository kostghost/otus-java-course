package ru.otus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.model.User;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class ExecutorDemo {
    private static final String CREATE_USER_TABLE_SQL = "" +
            "create table user(" +
            "   id bigint(20) not null auto_increment, " +
            "   name varchar(255), " +
            "   age int(3))";

    private static final String CREATE_ACCOUNT_TABLE_SQL = "" +
            "create table account(" +
            "   no bigint(20) not null auto_increment, " +
            "   type varchar(255), " +
            "   rest number)";

    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);

    public static void main(String[] args) throws SQLException {
        ExecutorDemo demo = new ExecutorDemo();

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            demo.createTable(connection, CREATE_USER_TABLE_SQL);
            demo.createTable(connection, CREATE_ACCOUNT_TABLE_SQL);


            DbExecutor<User> executor = new DbExecutor<>();
            long userId = executor.insertRecord(connection, "insert into user(name, age) values (?, ?)",
                    List.of("testUserName", "21"));
            logger.info("created user:{}", userId);
            connection.commit();

            Optional<User> user = executor.selectRecord(connection, "select id, name, age from user where id  = ?",
                    userId
                    , resultSet -> {
                        try {
                            if (resultSet.next()) {
                                return new User(
                                        resultSet.getLong("id"),
                                        resultSet.getString("name"),
                                        resultSet.getInt("age"));
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
            System.out.println(user);
        }
    }

    private void createTable(Connection connection, String sql) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }


}
