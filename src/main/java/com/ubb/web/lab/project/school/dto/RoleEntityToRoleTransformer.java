package com.ubb.web.lab.project.school.dto;

import com.ubb.web.lab.project.school.domain.Role;
import com.ubb.web.lab.project.school.domain.entity.RoleEntity;

public class RoleEntityToRoleTransformer {
    public Role transform(RoleEntity roleEntity) {
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }
}
