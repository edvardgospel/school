package com.ubb.web.lab.project.school.repository;

import java.util.List;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingRepository extends JpaRepository<Teaching, Integer> {
    Teaching findByUserAndSubject(User user, Subject subj);

    List<Teaching> findAllByUser(User user);
}
