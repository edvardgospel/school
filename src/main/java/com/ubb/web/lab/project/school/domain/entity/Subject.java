package com.ubb.web.lab.project.school.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    private List<Teaching> teachings;
}
