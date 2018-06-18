package com.ubb.web.lab.project.school.dto;

import com.ubb.web.lab.project.school.domain.entity.RoleEntity;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;
import com.ubb.web.lab.project.school.domain.request.NewUserRequest;
import com.ubb.web.lab.project.school.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class NewUserRequestToUserEntityTransformer {

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity transform(NewUserRequest request) {
        UserEntity entity = new UserEntity();
        Optional<RoleEntity> roleEntity;
        entity.setName(request.getName());
        if (request.getIsAdmin().booleanValue() == true) {
            roleEntity = roleRepository.findById(1);
        } else {
            roleEntity = roleRepository.findById(2);
        }
        entity.setRoleEntity(roleEntity.get());
        return entity;
    }
}
