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
import java.util.Objects;

@Controller
@RequestMapping(value = "/user")
public class AdminController {

    public static final String SUBJECTS = "subjects";
    public static final String USERS = "users";
    public static final String USER_DOES_NOT_EXISTS = "User does not exists.";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String LOGIN = "login";
    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SubjectManagerService subjectManagerService;

    @Autowired
    private SubjectValidatorService subjectValidatorService;

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String name, Model model) {
        User user = userManagerService.loginUser(name);
        if (Objects.isNull(user)) {
            model.addAttribute(ERROR_MESSAGE, USER_DOES_NOT_EXISTS);
            return LOGIN;
        }

        String role = user.getRole();
        if (role.equals(ADMIN)) {
            List<User> users = userManagerService.getUsers();
            List<Subject> subjects = userManagerService.getSubjectsByTeacherName(users.get(0).getName());
            model.addAttribute(USERS, users);
            model.addAttribute(SUBJECTS, subjects);
        } else if (role.equals(TEACHER)) {
        }
        return role;
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
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

    @ResponseBody
    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("name") String name) {
        userManagerService.deleteUser(name);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/";
    }
}
