package com.ubb.web.lab.project.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class SubjectValidatorService {

    @Autowired
    private SubjectManagerService subjectManagerService;

    public Boolean isValid(List<String> subjects) {
        List<String> allSubjects = subjectManagerService.getSubjectsWithNameAndGrade();
        return allSubjects.containsAll(subjects);
    }
}
