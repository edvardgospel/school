package com.ubb.web.lab.project.school.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.User;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;

public class UserEntityToUserTransformer {

    @Autowired
    RoleEntityToRoleTransformer transformer;

    public User transform(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setRole(transformer.transform(userEntity.getRoleEntity()));
        user.setName(userEntity.getName());
        return user;
    }
}
