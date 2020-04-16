package ru.otus.hw13.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw13.domain.User;
import ru.otus.hw13.services.UserService;

@Controller
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage.html";
    }

    @GetMapping("/admin/user/list")
    public String userListView(Model model) {
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "userList.html";
    }

    @GetMapping("/admin/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate.html";
    }

    @PostMapping("/admin/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        userService.save(user);
        return new RedirectView("/admin", true);
    }

}
