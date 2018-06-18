package com.ubb.web.lab.project.school.repository;

import com.ubb.web.lab.project.school.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    List<User> findAllByOrderByName();
}
