package com.ubb.web.lab.project.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubb.web.lab.project.school.domain.Subject;
import com.ubb.web.lab.project.school.service.SubjectManagerService;
import com.ubb.web.lab.project.school.service.UserManagerService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    public static final String SUBJECTS = "subjects";

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SubjectManagerService subjectManagerService;

    @RequestMapping(method = RequestMethod.POST)
    public String changeTeacher(@RequestParam("name") String name, Model model) {
        List<Subject> subjects = userManagerService.getSubjectsByTeacher(name);
        model.addAttribute(SUBJECTS, subjects);
        return "admin :: #subjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public List<String> subjects() {
        return subjectManagerService.getSubjectsWithNameAndGrade();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/";
    }
}
