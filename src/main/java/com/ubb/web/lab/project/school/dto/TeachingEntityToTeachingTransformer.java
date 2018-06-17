package com.ubb.web.lab.project.school.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.Teaching;
import com.ubb.web.lab.project.school.domain.entity.TeachingEntity;

public class TeachingEntityToTeachingTransformer {

    @Autowired
    private UserEntityToUserTransformer userTransformer;

    @Autowired
    private SubjectEntityToSubjectTransformer subjectTransformer;

    public Teaching transform(TeachingEntity teachingEntity) {
        Teaching teaching = new Teaching();
        teaching.setUser(userTransformer.transform(teachingEntity.getUserEntity()));
        teaching.setSubject(subjectTransformer.transform(teachingEntity.getSubjectEntity()));
        return teaching;
    }

    public List<Teaching> transformList(List<TeachingEntity> teachingEntities) {
        List<Teaching> teachings = new ArrayList<>();
        for (TeachingEntity teachingEntity : teachingEntities) {
            teachings.add(transform(teachingEntity));
        }
        return teachings;
    }
}
