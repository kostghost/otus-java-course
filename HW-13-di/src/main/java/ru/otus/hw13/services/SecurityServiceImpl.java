package ru.otus.hw13.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import ru.otus.hw13.domain.Role;
import ru.otus.hw13.domain.User;

/**
 * очень безопасный сервис
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;

    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isAdmin(String login, String password) {
        Optional<User> user = userService.getByLogin(login);

        return user
                .map(u -> checkPassword(login, password)
                        && u.getRole().equals(Role.ADMIN))
                .orElse(false);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        Optional<User> user = userService.getByLogin(login);

        return user
                .map(usr -> usr.getPassword().equals(password))
                .orElse(false);

    }
}
