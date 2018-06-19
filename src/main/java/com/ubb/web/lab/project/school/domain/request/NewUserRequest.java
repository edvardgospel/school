package com.ubb.web.lab.project.school.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class NewUserRequest {
    private String name;
    private Boolean isAdmin;
    private List<String> subjects;
}
