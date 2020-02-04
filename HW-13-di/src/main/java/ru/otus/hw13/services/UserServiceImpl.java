package ru.otus.hw13.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.hw13.core.sessionmanager.SessionManager;
import ru.otus.hw13.domain.User;
import ru.otus.hw13.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> save(User user) {
        try (SessionManager sessionManager = repository.sessionManager()) {
            sessionManager.beginSession();
            try {
                var savedUser = repository.save(user);
                sessionManager.commitSession();

                return savedUser;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                logger.error(e.getMessage(), e);
            }

            return Optional.empty();
        }
    }


    @Override
    public List<User> list() {
        try (SessionManager sessionManager = repository.sessionManager()) {
            sessionManager.beginSession();
            try {
                var users = repository.findAll();
                sessionManager.commitSession();

                return users;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                logger.error(e.getMessage(), e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try (SessionManager sessionManager = repository.sessionManager()) {
            sessionManager.beginSession();
            try {
                var user = repository.getByLogin(login);
                sessionManager.commitSession();

                return user;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                logger.error(e.getMessage(), e);
            }
        }

        return Optional.empty();
    }
}
