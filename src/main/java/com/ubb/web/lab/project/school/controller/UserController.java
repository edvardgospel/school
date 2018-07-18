package com.ubb.web.lab.project.school.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Timetable;
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
    public static final String CHANGE_SUBJECTS = "/change/subjects";
    public static final String ADMIN_TIMETABLE = "admin :: #timetable";
    public static final String ADMIN_SUBJECT_LIST = "admin :: #subjectList";
    public static final String CHANGE_TIMETABLE = "/change/timetable";
    public static final String CREATE = "/create";
    public static final String SUBJECTS1 = "/subjects";
    public static final String UPDATE = "/update";
    public static final String DELETE_NAME = "/delete/{name}";
    public static final String LOGOUT = "/logout";
    public static final String REDIRECT = "redirect:/";
    public static final String TIMETABLE = "/timetable";
    public static final String NAME = "name";

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
        List<User> users = userManagerService.getUsers();
        String selection = null;
        if (role.equals(ADMIN)) {
            selection = users.get(0).getName();
        } else if (role.equals(TEACHER)) {
            selection = user.getName();
        }

        List<Subject> subjects = userManagerService.getSubjectsByTeacherName(selection);
        List<Timetable> timetables = timetableManagerService.getTimetablesByTeacherName(selection);
        for (Timetable timetable : timetables) {
            if (Objects.nonNull(timetable)) {
                model.addAttribute(timetable.getDay() + timetable.getTime(), timetable.getTeaching().getSubject().getName() + timetable.getTeaching().getSubject().getGrade());
            }
        }
        model.addAttribute(USERS, users);
        model.addAttribute(SUBJECTS, subjects);
        model.addAttribute(USERNAME, name);
        return role;
    }

    @RequestMapping(value = CHANGE_SUBJECTS, method = RequestMethod.POST)
    public String changeSubjects(@RequestParam(NAME) String name, Model model) {
        List<Subject> subjects = userManagerService.getSubjectsByTeacherName(name);
        model.addAttribute(SUBJECTS, subjects);
        return ADMIN_SUBJECT_LIST;
    }

    @RequestMapping(value = CHANGE_TIMETABLE, method = RequestMethod.POST)
    public String changeTimetable(@RequestParam(NAME) String name, Model model) {
        List<Timetable> timetables = timetableManagerService.getTimetablesByTeacherName(name);
        for (Timetable timetable : timetables) {
            if (Objects.nonNull(timetable)) {
                model.addAttribute(timetable.getDay() + timetable.getTime(), timetable.getTeaching().getSubject().getName() + timetable.getTeaching().getSubject().getGrade());
            }
        }
        return ADMIN_TIMETABLE;
    }

    @ResponseBody
    @RequestMapping(value = SUBJECTS1, method = RequestMethod.GET)
    public List<String> getSubjects() {
        return subjectManagerService.getSubjectsWithNameAndGrade();
    }

    @ResponseBody
    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public Boolean createUser(String name, Boolean isAdmin, String subjects, Model model) {
        return userManagerService.createUser(name, isAdmin, subjects);
    }

    @ResponseBody
    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public Boolean updateUser(String name, Boolean isAdmin, String subjects, Model model) {
        return userManagerService.updateUser(name, isAdmin, subjects);
    }

    @ResponseBody
    @RequestMapping(value = DELETE_NAME, method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(NAME) String name) {
        userManagerService.deleteUser(name);
    }

    @RequestMapping(value = LOGOUT, method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return REDIRECT;
    }

    @ResponseBody
    @RequestMapping(value = TIMETABLE, method = RequestMethod.POST)
    public Boolean saveTimetable(@RequestBody TimetableRequest timetable) {
        return timetableManagerService.saveTimetables(timetable);
    }
}
