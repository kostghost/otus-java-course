package ru.otus.api.service;

import java.util.Optional;

import ru.otus.api.model.User;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);

}
