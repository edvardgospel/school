package com.ubb.web.lab.project.school.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.repository.SubjectRepository;

public class SubjectManagerService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<String> getSubjectsWithNameAndGrade() {
        return getSubjectListWithNameAndGrade(subjectRepository.findAll());
    }

    public Boolean subjectsAreValid(List<String> subjects) {
        List<String> allSubjects = getSubjectsWithNameAndGrade();
        if (allSubjects.containsAll(subjects)) {
            return true;
        }
        return false;
    }

    public List<String> split(String subjectsStr) {
        return Arrays.asList(subjectsStr.trim().split(" +"));
    }

    public List<String> getSubjectListWithNameAndGrade(List<Subject> subjects) {
        return subjects.stream().map(subject -> subject.getName() + subject.getGrade()).collect(Collectors.toList());
    }

    public Subject getSubjectByNameAndGrade(String subjectName, Integer grade) {
        return subjectRepository.findByNameAndGrade(subjectName, grade);
    }
}
