package com.ubb.web.lab.project.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubb.web.lab.project.school.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM UserEntity u WHERE u.name = ?1")
    UserEntity findByName(String name);

}
