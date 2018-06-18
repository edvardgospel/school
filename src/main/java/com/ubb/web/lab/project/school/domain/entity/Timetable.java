package com.ubb.web.lab.project.school.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "teachingId")
    private Teaching teaching;
    private String day;
    private Integer time;
}
