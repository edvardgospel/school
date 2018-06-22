package com.ubb.web.lab.project.school.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.repository.SubjectRepository;
import com.ubb.web.lab.project.school.repository.TeachingRepository;
import com.ubb.web.lab.project.school.repository.UserRepository;

public class UserManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeachingRepository teachingRepository;

    @Autowired
    private SubjectManagerService subjectManagerService;

    public Boolean saveUser(String name, Boolean isAdmin, String subjectsStr) {
        List<String> subjects = subjectManagerService.split(subjectsStr);
        if (nameIsValid(name) && subjectManagerService.subjectsAreValid(subjects)) {
            saveNewUser(name, isAdmin, subjects);
            return true;
        }
        return false;
    }

    private void saveNewUser(String name, Boolean isAdmin, List<String> subjects) {
        User user = new User();
        user.setName(name);
        if (isAdmin) {
            user.setRole("admin");
        } else {
            user.setRole("teacher");
        }
        userRepository.save(user);
        user = getUser(name);
        List<Teaching> teachings = new ArrayList<>();
        for (String subjectAndGrade : subjects) {
            Subject subject = subjectRepository.findByNameAndGrade(getSubjectName(subjectAndGrade), getGrade(subjectAndGrade));
            Teaching teaching = new Teaching();
            teaching.setUser(user);
            teaching.setSubject(subject);
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
        List<Subject> subjects = new ArrayList<>();
        for (Teaching teaching : teachings) {
            subjects.add(teaching.getSubject());
        }
        return subjects;
    }

    public void deleteUser(String name) {
        User user = userRepository.findByName(name);
        userRepository.delete(user);
    }

    private Integer getGrade(String subject) {
        return Integer.valueOf(subject.substring(subject.length() - 1));
    }

    private String getSubjectName(String subject) {
        return subject.substring(0, subject.length() - 1);
    }

    private List<String> removeDuplicatesFromList(List<String> strings) {
        Set<String> teachingSet = new HashSet<>();
        teachingSet.addAll(strings);
        strings.clear();
        strings.addAll(teachingSet);
        return strings;
    }

}