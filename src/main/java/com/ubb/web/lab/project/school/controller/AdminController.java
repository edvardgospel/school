package com.ubb.web.lab.project.school.controller;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.domain.request.NewUserRequest;
import com.ubb.web.lab.project.school.service.SubjectManagerService;
import com.ubb.web.lab.project.school.service.SubjectValidatorService;
import com.ubb.web.lab.project.school.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    public static final String SUBJECTS = "subjects";
    public static final String USERS = "users";

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SubjectManagerService subjectManagerService;

    @Autowired
    private SubjectValidatorService subjectValidatorService;

    @RequestMapping(method = RequestMethod.POST)
    public String changeTeacher(@RequestParam("name") String name, Model model) {
        List<Subject> subjects = userManagerService.getSubjectsByTeacherName(name);
        model.addAttribute(SUBJECTS, subjects);
        return "admin :: #subjectList";
    }

    @ResponseBody
    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public List<String> subjects() {
        return subjectManagerService.getSubjectsWithNameAndGrade();
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewUser(@RequestBody NewUserRequest newUser, Model model) {
        userManagerService.saveNewUser(newUser);
        List<User> users = userManagerService.getUsers();
        List<Subject> subjects = userManagerService.getSubjectsByTeacherName(newUser.getName());
        model.addAttribute(USERS, users);
        model.addAttribute(SUBJECTS, subjects);
        return "admin :: #subjectList";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("name") String name) {
System.out.println("name: " + name);
        userManagerService.deleteUser(name);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/";
    }
}
