package com.ubb.web.lab.project.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.Subject;
import com.ubb.web.lab.project.school.domain.Teaching;
import com.ubb.web.lab.project.school.domain.User;
import com.ubb.web.lab.project.school.dto.SubjectEntityToSubjectTransformer;
import com.ubb.web.lab.project.school.dto.TeachingEntityToTeachingTransformer;
import com.ubb.web.lab.project.school.dto.UserEntityToUserTransformer;
import com.ubb.web.lab.project.school.repository.TeachingRepository;
import com.ubb.web.lab.project.school.repository.UserRepository;

public class UserManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeachingRepository teachingRepository;

    @Autowired
    private UserEntityToUserTransformer userTransformer;

    @Autowired
    private SubjectEntityToSubjectTransformer subjectTransformer;

    @Autowired
    private TeachingEntityToTeachingTransformer teachingTransformer;

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
}
