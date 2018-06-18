package com.ubb.web.lab.project.school.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer grade;

    @OneToMany(mappedBy = "subject")
    private Set<Teaching> teachings;
}
