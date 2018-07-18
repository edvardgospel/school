package com.ubb.web.lab.project.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.repository.TeachingRepository;

public class TeachingManagerService {

    @Autowired
    private TeachingRepository teachingRepository;


    public List<Teaching> getTeachingsByUser(User user) {
        return teachingRepository.findAllByUser(user);
    }

    public Teaching getTeachingByUserAndSubject(User user, Subject subject) {
        return teachingRepository.findByUserAndSubject(user, subject);
    }

    public void saveTeaching(Teaching teaching) {
        teachingRepository.save(teaching);
    }
}
