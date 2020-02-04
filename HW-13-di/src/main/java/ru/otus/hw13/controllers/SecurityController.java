package ru.otus.hw13.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw13.domain.Role;
import ru.otus.hw13.domain.User;
import ru.otus.hw13.services.SecurityService;
import ru.otus.hw13.services.UserService;
import ru.otus.hw13.view.LoginResponseView;

@RestController
public class SecurityController {

    private final SecurityService securityService;
    private final UserService userService;

    public SecurityController(SecurityService securityService,
                              UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/initialize")
    public void initialize() {
        var user = new User("admin", "admin", "admin", Role.ADMIN);

        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    public LoginResponseView login(@RequestBody LoginRequest request) {

        boolean loginSuccessful = securityService.checkPassword(request.getLogin(), request.getPassword());
        boolean isAdmin = securityService.isAdmin(request.getLogin(), request.getPassword());

        return new LoginResponseView(loginSuccessful, isAdmin);
    }

    private class LoginRequest {
        private String login;
        private String password;

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }
}
