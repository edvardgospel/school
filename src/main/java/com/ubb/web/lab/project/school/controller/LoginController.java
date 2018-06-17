package com.ubb.web.lab.project.school.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubb.web.lab.project.school.domain.Role;
import com.ubb.web.lab.project.school.domain.User;
import com.ubb.web.lab.project.school.service.UserManagerService;
import com.ubb.web.lab.project.school.service.UserValidatorService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    public static final String USER_DOES_NOT_EXISTS = "User does not exists.";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String LOGIN = "login";
    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";
    public static final String USERS = "users";

    @Autowired
    private UserValidatorService userValidatorService;

    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return LOGIN;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String name, Model model) {
        Role role = userValidatorService.validate(name);
        if (Objects.isNull(role)) {
            model.addAttribute(ERROR_MESSAGE, USER_DOES_NOT_EXISTS);
            return LOGIN;
        }
        String roleName = role.getName();
        if (roleName.equals(ADMIN)) {
            List<User> users = userManagerService.getUsers();
            model.addAttribute(USERS,users);
        } else if (roleName.equals(TEACHER)) {
            userManagerService.getSubjects(name);
        }
        return roleName;
    }
}
