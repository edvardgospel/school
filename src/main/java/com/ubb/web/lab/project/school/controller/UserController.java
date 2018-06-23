package com.ubb.web.lab.project.school.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.domain.request.TimetableRequest;
import com.ubb.web.lab.project.school.service.SubjectManagerService;
import com.ubb.web.lab.project.school.service.SubjectValidatorService;
import com.ubb.web.lab.project.school.service.TimetableManagerService;
import com.ubb.web.lab.project.school.service.UserManagerService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    public static final String SUBJECTS = "subjects";
    public static final String USERS = "users";
    public static final String USER_DOES_NOT_EXISTS = "User does not exists.";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String LOGIN = "login";
    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";
    public static final String USERNAME = "userName";

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SubjectManagerService subjectManagerService;

    @Autowired
    private SubjectValidatorService subjectValidatorService;

    @Autowired
    private TimetableManagerService timetableManagerService;

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String name, Model model, HttpSession httpSession) {
        User user = userManagerService.getUser(name);
        if (Objects.isNull(user)) {
            model.addAttribute(ERROR_MESSAGE, USER_DOES_NOT_EXISTS);
            return LOGIN;
        }

        String role = user.getRole();
        httpSession.setAttribute(USERNAME, name);
        if (role.equals(ADMIN)) {
            List<User> users = userManagerService.getUsers();
            List<Subject> subjects = userManagerService.getSubjectsByTeacherName(users.get(0).getName());
            model.addAttribute(USERS, users);
            model.addAttribute(SUBJECTS, subjects);
            model.addAttribute(USERNAME, name);
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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean createUser(String name, Boolean isAdmin, String subjects, Model model) {
        return userManagerService.createUser(name, isAdmin, subjects);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Boolean updateUser(String name, Boolean isAdmin, String subjects, Model model) {
        return userManagerService.updateUser(name, isAdmin, subjects);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("name") String name) {
        userManagerService.deleteUser(name);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/timetable", method = RequestMethod.POST)
    public String timetable(TimetableRequest timetable) {
        System.out.println(timetable.toString());
        //timetableManagerService.saveTimetables();
        return ADMIN;
    }
}
