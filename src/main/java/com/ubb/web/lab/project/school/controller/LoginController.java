package com.ubb.web.lab.project.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    public static final String LOGIN = "login";

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return LOGIN;
    }

}
