package com.ubb.web.lab.project.school.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.repository.UserRepository;

public class UserManagerService {

    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectManagerService subjectManagerService;

    @Autowired
    private TeachingManagerService teachingManagerService;

    public Boolean createUser(String name, Boolean isAdmin, String subjectsStr) {
        List<String> subjects = subjectManagerService.split(subjectsStr);
        if (nameIsValid(name) && subjectManagerService.subjectsAreValid(subjects)) {
            saveUser(name, isAdmin, subjects);
            return true;
        }
        return false;
    }

    public Boolean updateUser(String name, Boolean isAdmin, String subjectsStr) {
        List<String> subjects = subjectManagerService.split(subjectsStr);
        if (nameExists(name) && subjectManagerService.subjectsAreValid(subjects)) {
            deleteUser(name);
            saveUser(name, isAdmin, subjects);
            return true;
        }
        return false;
    }

    private Boolean nameExists(String name) {
        return Objects.nonNull(getUser(name));
    }


    private void saveUser(String name, Boolean isAdmin, List<String> subjects) {
        User user = new User();
        user.setName(name);
        if (isAdmin) {
            user.setRole(ADMIN);
        } else {
            user.setRole(TEACHER);
        }
        userRepository.save(user);
        user = getUser(name);
        List<Teaching> teachings = new ArrayList<>();
        for (String subjectAndGrade : subjects) {
            Subject subject = subjectManagerService.getSubjectByNameAndGrade(getSubjectName(subjectAndGrade), getGrade(subjectAndGrade));
            Teaching teaching = new Teaching();
            teaching.setUser(user);
            teaching.setSubject(subject);
            teachingManagerService.saveTeaching(teaching);
            teachings.add(teaching);
        }
        user.setTeachings(teachings);
    }

    private Boolean nameIsValid(String name) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getName().toLowerCase().equals(name.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public User getUser(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Subject> getSubjectsByTeacherName(String name) {
        User user = userRepository.findByName(name);
        return getSubjectsByTeachings(user.getTeachings());
    }

    private List<Subject> getSubjectsByTeachings(List<Teaching> teachings) {
        return teachings.stream().map(Teaching::getSubject).collect(Collectors.toList());
    }

    public void deleteUser(String name) {
        userRepository.delete(userRepository.findByName(name));
    }

    public Integer getGrade(String subject) {
        return Integer.valueOf(subject.substring(subject.length() - 1));
    }

    public String getSubjectName(String subject) {
        return subject.substring(0, subject.length() - 1);
    }

}