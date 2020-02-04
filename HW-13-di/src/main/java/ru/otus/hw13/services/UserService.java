package ru.otus.hw13.services;

import java.util.List;
import java.util.Optional;

import ru.otus.hw13.domain.User;

public interface UserService {
    Optional<User> save(User user);

    List<User> list();

    Optional<User> getByLogin(String login);

}
