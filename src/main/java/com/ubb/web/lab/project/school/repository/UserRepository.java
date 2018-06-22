package com.ubb.web.lab.project.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubb.web.lab.project.school.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
