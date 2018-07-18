package com.ubb.web.lab.project.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    public static final String HOME = "home";
    public static final String SLASH = "/";

    @RequestMapping(value = SLASH, method = RequestMethod.GET)
    public String home() {
        return HOME;
    }
}
