package com.ubb.web.lab.project.school.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private Role role;
    private String name;
}
