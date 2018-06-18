package com.ubb.web.lab.project.school.repository;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findByNameAndGrade(String name, Integer grade);
}
