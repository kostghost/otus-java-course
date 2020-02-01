package ru.otus.jdbc;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlTemplateGeneratorImplTest {

    private SqlTemplateGenerator sqlGenerator;

    @BeforeEach
    void init() {
        sqlGenerator = new SqlTemplateGeneratorImpl();
    }

    @Test
    void insert() {
        var names = List.of("nameOne", "nameTwo", "nameThree");
        var tableName = "tableName";

        String expected = "insert into tableName (nameOne, nameTwo, nameThree) values (?, ?, ?)";

        assertEquals(expected, sqlGenerator.insert(tableName, names));
    }

    @Test
    void update() {
        var names = List.of("nameOne", "nameTwo", "nameThree");
        var keyName = "key";
        var tableName = "tableName";

        String expected = "update tableName set nameOne = ?, nameTwo = ?, nameThree = ? where key = ?";

        assertEquals(expected, sqlGenerator.update(tableName, names, keyName));
    }

    @Test
    void select() {
        var names = List.of("nameOne", "nameTwo", "nameThree");
        var keyName = "key";
        var tableName = "tableName";

        String expected = "select nameOne, nameTwo, nameThree from tableName where key = ?";

        assertEquals(expected, sqlGenerator.select(tableName, names, keyName));

    }
}