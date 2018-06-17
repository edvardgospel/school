package com.ubb.web.lab.project.school.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "teaching")
@IdClass(TeachingEntity.class)
public class TeachingEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;
}
