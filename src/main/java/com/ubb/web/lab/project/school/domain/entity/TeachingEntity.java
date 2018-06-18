package com.ubb.web.lab.project.school.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.ubb.web.lab.project.school.domain.User;
import lombok.Data;

@Data
@Entity
@Table(name = "teaching")
@IdClass(TeachingEntity.class)
public class TeachingEntity implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;
}
