package com.ubb.web.lab.project.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    void deleteByTeaching(Teaching teaching);

    List<Timetable> findByTeaching(Teaching teaching);
}
