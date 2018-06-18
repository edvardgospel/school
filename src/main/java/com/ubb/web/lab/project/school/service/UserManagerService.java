package com.ubb.web.lab.project.school.service;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.domain.request.NewUserRequest;
import com.ubb.web.lab.project.school.dto.NewUserRequestToUserEntityTransformer;
import com.ubb.web.lab.project.school.repository.SubjectRepository;
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
    private NewUserRequestToUserEntityTransformer requestToUserEntityTransformer;

    public List<User> getUsers() {
        return userRepository.findAllByOrderByName();
    }

    public List<Subject> getSubjectsByTeacher(String name) {
        User user = userRepository.findByName(name);
        List<Teaching> teachings = null;/////TODO
        List<Subject> subjects = new ArrayList<>();
        for (Teaching teaching : teachings) {
            subjects.add(teaching.getSubject());
        }
        return subjects;
    }

    private List<Subject> getSubjectsByTeachings(List<Teaching> teachings) {
        List<Subject> subjects = new ArrayList<>();
        for (Teaching teaching : teachings) {
            subjects.add(teaching.getSubject());
        }
        return subjects;
    }

    public void saveNewUser(NewUserRequest newUser) {
        User user = requestToUserEntityTransformer.transform(newUser);
        userRepository.save(user);
    }

    public void saveNewUserTeachings(NewUserRequest newUser) {
        /*UserEntity userEntity = userRepository.findByName(newUser.getName());
        List<String> teachings = removeDuplicatesFromList(newUser.getTeaching());
        for (String teaching : teachings) {
            String subjectName = getSubjectName(teaching);
            Integer grade = getGrade(teaching);
            TeachingEntity entity = new TeachingEntity();
            SubjectEntity subjectEntity = subjectRepository.findByNameAndGrade(subjectName, grade);
            entity.setUserEntity(userEntity);
            entity.setSubjectEntity(subjectEntity);
            System.out.println(entity);
            teachingRepository.saveAndFlush(entity);*/
    }

    private Integer getGrade(String teaching) {
        return Integer.valueOf(teaching.substring(teaching.length() - 1));
    }

    private String getSubjectName(String teaching) {
        return teaching.substring(0, teaching.length() - 1);
    }

    private List<String> removeDuplicatesFromList(List<String> teachings) {
        Set<String> teachingSet = new HashSet<>();
        teachingSet.addAll(teachings);
        teachings.clear();
        teachings.addAll(teachingSet);
        return teachings;
    }
}