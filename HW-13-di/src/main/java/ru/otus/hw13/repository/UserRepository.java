package ru.otus.hw13.repository;

import java.util.List;
import java.util.Optional;

import ru.otus.hw13.core.sessionmanager.SessionManager;
import ru.otus.hw13.domain.User;

public interface UserRepository {
    Optional<User> save(User user);

    List<User> findAll();

    Optional<User> getByLogin(String login);

    SessionManager sessionManager();
}
