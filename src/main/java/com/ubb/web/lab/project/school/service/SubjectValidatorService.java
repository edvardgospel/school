package com.ubb.web.lab.project.school.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubjectValidatorService {

    @Autowired
    private SubjectManagerService subjectManagerService;

    public Boolean isValid(List<String> subjects) {
        Boolean valid = true;
        List<String> allSubjects = subjectManagerService.getSubjectsWithNameAndGrade();
        return allSubjects.containsAll(subjects);
    }
}
