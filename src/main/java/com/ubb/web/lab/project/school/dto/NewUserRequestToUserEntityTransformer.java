package com.ubb.web.lab.project.school.dto;

import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.domain.request.NewUserRequest;

public class NewUserRequestToUserEntityTransformer {

    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";

    public User transform(NewUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        if (request.getIsAdmin()) {
            user.setRole(ADMIN);
        } else {
            user.setRole(TEACHER);
        }
        return user;
    }
}
