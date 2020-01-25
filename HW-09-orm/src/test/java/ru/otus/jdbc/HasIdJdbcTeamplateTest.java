package ru.otus.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.api.h2.DataSourceH2;
import ru.otus.api.model.User;
import ru.otus.api.sessionmanager.SessionManager;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;
import ru.otus.reflection.ObjectMapper;
import ru.otus.reflection.ObjectMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HasIdJdbcTeamplateTest {
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
    private SessionManager sessionManager;
    private DbExecutor dbExecutor;
    private DataSource dataSource;
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() throws Exception {
        this.dataSource = new DataSourceH2();
        this.sessionManager = new SessionManagerJdbc(dataSource);
        this.dbExecutor = new DbExecutor();
        this.objectMapper = new ObjectMapperImpl();

        createTable(dataSource, CREATE_ACCOUNT_TABLE_SQL);
        createTable(dataSource, CREATE_USER_TABLE_SQL);
    }

    @Test
    void create() throws SQLException {
        var userJdbcTemlate = new HasIdJdbcTeamplate<User>(sessionManager, dbExecutor, objectMapper);
        var originalPupokin = new User(9, "Вася Пупокин", 42);

        userJdbcTemlate.create(originalPupokin);
        userJdbcTemlate.update(originalPupokin);
        assertEquals("Вася Пупокин", selectFirstUserName(dataSource));
    }

    @Test
    void justTest() {
        var userJdbcTemlate = new HasIdJdbcTeamplate<User>(sessionManager, dbExecutor, objectMapper);
        var originalPupokin = new User(9, "Вася Пупокин", 42);

        userJdbcTemlate.create(originalPupokin);
        User risenPupokin = userJdbcTemlate.load(9, User.class);

        assertEquals(originalPupokin, risenPupokin);
    }


    private void createTable(DataSource dataSource, String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }

    private String selectFirstUserName(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("select * from user")) {
            var rows = pst.executeQuery();
            rows.next();
            return rows.getString("name");

        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement("DROP ALL OBJECTS DELETE FILES")) {
            pst.execute();
        }
    }
}