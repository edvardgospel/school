package com.ubb.web.lab.project.school.service;

import com.ubb.web.lab.project.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidatorService {

    @Autowired
    private UserRepository userRepository;

    public String validate(String name) {
        return userRepository.findByName(name).getRole();
    }

}
