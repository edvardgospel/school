package com.ubb.web.lab.project.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubb.web.lab.project.school.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByName(String name);

    List<UserEntity> findAllByOrderByName();

}
