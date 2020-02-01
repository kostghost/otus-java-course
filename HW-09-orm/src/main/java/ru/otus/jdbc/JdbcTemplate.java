package ru.otus.jdbc;


public interface JdbcTemplate<T> {
    void create(T objectData);

    void update(T objectData);

    T load(long id, Class<T> clazz);
}
