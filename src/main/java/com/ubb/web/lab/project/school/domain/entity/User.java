package com.ubb.web.lab.project.school.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Teaching> teachings;
}
