package com.ubb.web.lab.project.school.service;

import com.ubb.web.lab.project.school.domain.Subject;
import com.ubb.web.lab.project.school.domain.Teaching;
import com.ubb.web.lab.project.school.domain.User;
import com.ubb.web.lab.project.school.domain.entity.SubjectEntity;
import com.ubb.web.lab.project.school.domain.entity.TeachingEntity;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;
import com.ubb.web.lab.project.school.domain.request.NewUserRequest;
import com.ubb.web.lab.project.school.dto.NewUserRequestToUserEntityTransformer;
import com.ubb.web.lab.project.school.dto.SubjectEntityToSubjectTransformer;
import com.ubb.web.lab.project.school.dto.TeachingEntityToTeachingTransformer;
import com.ubb.web.lab.project.school.dto.UserEntityToUserTransformer;
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
    private TeachingRepository teachingRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserEntityToUserTransformer userTransformer;

    @Autowired
    private SubjectEntityToSubjectTransformer subjectTransformer;

    @Autowired
    private TeachingEntityToTeachingTransformer teachingTransformer;

    @Autowired
    private NewUserRequestToUserEntityTransformer requestToUserEntityTransformer;

    public List<User> getUsers() {
        return userTransformer.transformList(userRepository.findAllByOrderByName());
    }

    public List<Subject> getSubjectsByTeacher(String name) {
        return getSubjectsByTeachings(teachingTransformer.transformList(teachingRepository.findAllByUserId(userRepository.findByName(name))));
    }

    private List<Subject> getSubjectsByTeachings(List<Teaching> teachings) {
        List<Subject> subjects = new ArrayList<>();
        for (Teaching teaching : teachings) {
            subjects.add(teaching.getSubject());
        }
        return subjects;
    }

    public void saveNewUser(NewUserRequest newUser) {
        UserEntity userEntity = requestToUserEntityTransformer.transform(newUser);
        userRepository.save(userEntity);
    }

    public void saveNewUserTeachings(NewUserRequest newUser) {
        UserEntity userEntity = userRepository.findByName(newUser.getName());
        List<String> teachings = removeDuplicatesFromList(newUser.getTeaching());
        for (String teaching : teachings) {
            String subjectName = getSubjectName(teaching);
            Integer grade = getGrade(teaching);
            TeachingEntity entity = new TeachingEntity();
            SubjectEntity subjectEntity = subjectRepository.findByNameAndGrade(subjectName, grade);
            entity.setUserEntity(userEntity);
            entity.setSubjectEntity(subjectEntity);
            System.out.println(entity);
            teachingRepository.saveAndFlush(entity);
        }
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