package com.ubb.web.lab.project.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubb.web.lab.project.school.domain.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity,Integer> {

    List<SubjectEntity> findAllByName(String name);
}
