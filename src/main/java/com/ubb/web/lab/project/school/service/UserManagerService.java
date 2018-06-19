package com.ubb.web.lab.project.school.service;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.domain.request.NewUserRequest;
import com.ubb.web.lab.project.school.dto.NewUserRequestToUserTransformer;
import com.ubb.web.lab.project.school.repository.SubjectRepository;
import com.ubb.web.lab.project.school.repository.TeachingRepository;
import com.ubb.web.lab.project.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeachingRepository teachingRepository;

    @Autowired
    private NewUserRequestToUserTransformer requestToUserTransformer;

    public User loginUser(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsers() {
        return userRepository.findAllByOrderByName();
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

    public void saveNewUser(NewUserRequest newUser) {
        User user = requestToUserTransformer.transform(newUser);
        List<String> subjects = removeDuplicatesFromList(newUser.getSubjects());
        List<Teaching> teachings = new ArrayList<>();
        for (String subjectAndGrade : subjects) {
            String subjectStr = getSubjectName(subjectAndGrade);
            Integer grade = getGrade(subjectAndGrade);
            Subject subject = subjectRepository.findByNameAndGrade(subjectStr, grade);
            userRepository.save(user);
            User user1 = userRepository.findByName(user.getName());
            Teaching teaching = new Teaching();
            teaching.setUser(user1);
            teaching.setSubject(subject);
            teachingRepository.save(teaching);
            teachings.add(teaching);
        }
        user.setTeachings(teachings);

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