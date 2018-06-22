package com.ubb.web.lab.project.school.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println(subjects);
        System.out.println();
        System.out.println(allSubjects);
        if (allSubjects.containsAll(subjects)) {
            return true;
        }
        return false;
    }

    public List<String> split(String subjectsStr) {
        return Arrays.asList(subjectsStr.trim().split(" +"));
    }

    private List<String> getSubjectListWithNameAndGrade(List<Subject> subjects) {
        List<String> strings = new ArrayList<>();
        for (Subject subject : subjects) {
            strings.add(subject.getName() + subject.getGrade());
        }
        return strings;
    }

}
