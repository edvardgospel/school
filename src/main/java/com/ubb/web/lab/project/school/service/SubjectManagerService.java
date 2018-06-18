package com.ubb.web.lab.project.school.service;

import com.ubb.web.lab.project.school.domain.Subject;
import com.ubb.web.lab.project.school.dto.SubjectEntityToSubjectTransformer;
import com.ubb.web.lab.project.school.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SubjectManagerService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectEntityToSubjectTransformer subjectTransformer;

    public List<String> getSubjectsWithNameAndGrade() {
        return getSubjectListWithNameAndGrade(subjectTransformer.transformList(subjectRepository.findAll()));
    }

    private List<String> getSubjectListWithNameAndGrade(List<Subject> subjects) {
        List<String> strings = new ArrayList<>();
        for (Subject subject : subjects) {
            strings.add(subject.getName() + subject.getGrade());
        }
        return strings;
    }

}
