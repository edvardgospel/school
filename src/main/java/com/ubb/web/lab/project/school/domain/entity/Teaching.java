package com.ubb.web.lab.project.school.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teaching")
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;
}
