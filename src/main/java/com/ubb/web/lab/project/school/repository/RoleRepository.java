package com.ubb.web.lab.project.school.repository;

import com.ubb.web.lab.project.school.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
}
