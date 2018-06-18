package com.ubb.web.lab.project.school.repository;

import com.ubb.web.lab.project.school.domain.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    SubjectEntity findByNameAndGrade(String name, Integer grade);
}
