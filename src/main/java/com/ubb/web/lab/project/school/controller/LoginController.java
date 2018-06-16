package com.ubb.web.lab.project.school.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubb.web.lab.project.school.domain.Role;
import com.ubb.web.lab.project.school.service.UserValidatorService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    public static final String USER_DOES_NOT_EXISTS = "User does not exists.";

    @Autowired
    private UserValidatorService userValidatorService;

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String name, Model model) {
        Role role = userValidatorService.validate(name);
        if (Objects.isNull(role)) {
            model.addAttribute("errorMessage", USER_DOES_NOT_EXISTS);
            return "login";
        }
        return role.getName();
    }
}
