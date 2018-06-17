package com.ubb.web.lab.project.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubb.web.lab.project.school.domain.entity.SubjectEntity;
import com.ubb.web.lab.project.school.domain.entity.TeachingEntity;
import com.ubb.web.lab.project.school.domain.entity.UserEntity;

public interface TeachingRepository extends JpaRepository<TeachingEntity,Integer> {
    @Query("SELECT t FROM TeachingEntity t WHERE t.userEntity = ?1")
    List<TeachingEntity> findAllByUserId(UserEntity userEntity);
}
