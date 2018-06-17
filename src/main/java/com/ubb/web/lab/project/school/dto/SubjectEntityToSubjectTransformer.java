package com.ubb.web.lab.project.school.dto;

import java.util.ArrayList;
import java.util.List;

import com.ubb.web.lab.project.school.domain.Subject;
import com.ubb.web.lab.project.school.domain.User;
import com.ubb.web.lab.project.school.domain.entity.SubjectEntity;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;

public class SubjectEntityToSubjectTransformer {

    public Subject transform(SubjectEntity subjectEntity) {
        Subject subject = new Subject();
        subject.setId(subjectEntity.getId());
        subject.setName(subjectEntity.getName());
        subject.setGrade(subjectEntity.getGrade());
        return subject;
    }

    public List<Subject> transformList(List<SubjectEntity> subjectEntities) {
        List<Subject> subjects = new ArrayList<>();
        for (SubjectEntity subjectEntity : subjectEntities) {
            subjects.add(transform(subjectEntity));
        }
        return subjects;
    }
}
