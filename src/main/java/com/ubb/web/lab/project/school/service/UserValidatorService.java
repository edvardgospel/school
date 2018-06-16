package com.ubb.web.lab.project.school.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.Role;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;
import com.ubb.web.lab.project.school.dto.RoleEntityToRoleTransformer;
import com.ubb.web.lab.project.school.repository.UserRepository;

public class UserValidatorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityToRoleTransformer transformer;

    public Role validate(String name) {
        UserEntity userEntity = userRepository.findByName(name);
        Role role = null;
        if (exists(userEntity)) {
            role = getAuthorization(userEntity);
        }
        return role;
    }

    private Role getAuthorization(UserEntity userEntity) {
        return transformer.transform(userEntity.getRoleEntity());
    }

    private Boolean exists(UserEntity userEntity) {
        return Objects.nonNull(userEntity);
    }
}
