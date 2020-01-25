package ru.otus.reflection;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.api.annotations.Id;
import ru.otus.api.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ObjectMapperImplTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapperImpl();
    }

    @Test
    void getObjectFieldMap() {
        // по-хорошему нужно использовать не реальные, а специальные тестовые объекты
        var user = new User(9, "Vasyan Pupokin", 42);

        var objectMap = objectMapper.getObjectFieldMap(user);

        assertIterableEquals(
                List.of("age", "id", "name"),
                objectMap.keySet());
        assertIterableEquals(
                List.of(42, 9L, "Vasyan Pupokin"),
                objectMap.values());
    }

    @Test
    void getFieldNamesWithAnnotaion() {
        var user = new User(9, "Vasyan Pupokin", 42);
        List<String> fields = objectMapper.getFieldNamesWithAnnotaion(user, Id.class);

        assertIterableEquals(List.of("id"), fields);
    }

    @Test
    void getObjectClassName() {
        String name = objectMapper.getObjectClassName(new User(9, "", 0));

        assertEquals("User", name);
    }
}