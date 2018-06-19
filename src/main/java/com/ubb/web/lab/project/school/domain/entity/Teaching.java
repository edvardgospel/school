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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
