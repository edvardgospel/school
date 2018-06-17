package com.ubb.web.lab.project.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.ubb.web.lab.project.school.domain.User;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;
import com.ubb.web.lab.project.school.dto.UserEntityToUserTransformer;
import com.ubb.web.lab.project.school.repository.UserRepository;

public class UserManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityToUserTransformer transformer;

    public List<User> getUsers() {
        List<UserEntity> userEntities = userRepository.findAllByOrderByName();
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(transformer.transform(userEntity));
        }
        return users;
    }
}
