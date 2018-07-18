package com.ubb.web.lab.project.school.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.lab.project.school.domain.entity.Subject;
import com.ubb.web.lab.project.school.domain.entity.Teaching;
import com.ubb.web.lab.project.school.domain.entity.Timetable;
import com.ubb.web.lab.project.school.domain.entity.User;
import com.ubb.web.lab.project.school.domain.request.TimetableRequest;
import com.ubb.web.lab.project.school.repository.TimetableRepository;

@Transactional
public class TimetableManagerService {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private SubjectManagerService subjectManagerService;

    @Autowired
    private TeachingManagerService teachingManagerService;

    @Autowired
    private TimetableRepository timetableRepository;

    public Boolean saveTimetables(TimetableRequest timetable) {
        if (timetableIsValid(timetable)) {
            deleteTimetable(timetable);
            saveTimetable(timetable);
            return true;
        }
        return false;
    }

    private Boolean timetableIsValid(TimetableRequest timetable) {
        List<String> teacherSubjects = subjectManagerService.getSubjectListWithNameAndGrade(
                userManagerService.getSubjectsByTeacherName(timetable.getName()));
        teacherSubjects.add("");

        List<String> mondaySubjects = timetable.getMonday();
        List<String> tuesdaySubjects = timetable.getTuesday();
        List<String> wednesdaySubjects = timetable.getWednesday();
        List<String> thursdaySubjects = timetable.getThursday();
        List<String> fridaySubjects = timetable.getFriday();

        if (teacherSubjects.containsAll(mondaySubjects) &&
                teacherSubjects.containsAll(tuesdaySubjects) &&
                teacherSubjects.containsAll(wednesdaySubjects) &&
                teacherSubjects.containsAll(thursdaySubjects) &&
                teacherSubjects.containsAll(fridaySubjects)) {
            return true;
        }
        return false;
    }

    private void deleteTimetable(TimetableRequest timetable) {
        User user = userManagerService.getUser(timetable.getName());
        List<Teaching> teachings = teachingManagerService.getTeachingsByUser(user);
        for (Teaching teaching : teachings) {
            timetableRepository.deleteByTeaching(teaching);
        }
    }

    private void saveTimetable(TimetableRequest timetable) {
        String name = timetable.getName();
        List<String> mondaySubjects = timetable.getMonday();
        List<String> tuesdaySubjects = timetable.getTuesday();
        List<String> wednesdaySubjects = timetable.getWednesday();
        List<String> thursdaySubjects = timetable.getThursday();
        List<String> fridaySubjects = timetable.getFriday();
        for (int i = 0; i < mondaySubjects.size(); i++) {
            if (!mondaySubjects.get(i).equals("")) {
                saveTimetable(name, mondaySubjects.get(i), "monday", i + 8);
            }
        }
        for (int i = 0; i < tuesdaySubjects.size(); i++) {
            if (!tuesdaySubjects.get(i).equals("")) {
                saveTimetable(name, tuesdaySubjects.get(i), "tuesday", i + 8);
            }
        }
        for (int i = 0; i < wednesdaySubjects.size(); i++) {
            if (!wednesdaySubjects.get(i).equals("")) {
                saveTimetable(name, wednesdaySubjects.get(i), "wednesday", i + 8);
            }
        }
        for (int i = 0; i < thursdaySubjects.size(); i++) {
            if (!thursdaySubjects.get(i).equals("")) {
                saveTimetable(name, thursdaySubjects.get(i), "thursday", i + 8);
            }
        }
        for (int i = 0; i < fridaySubjects.size(); i++) {
            if (!fridaySubjects.get(i).equals("")) {
                saveTimetable(name, fridaySubjects.get(i), "friday", i + 8);
            }
        }
    }

    private void saveTimetable(String name, String subject, String day, Integer time) {
        Timetable timetable = new Timetable();
        String subjectName = userManagerService.getSubjectName(subject);
        Integer grade = userManagerService.getGrade(subject);
        timetable.setDay(day);
        timetable.setTime(time);
        User user = userManagerService.getUser(name);
        Subject subj = subjectManagerService.getSubjectByNameAndGrade(subjectName, grade);
        Teaching teaching = teachingManagerService.getTeachingByUserAndSubject(user, subj);
        timetable.setTeaching(teaching);
        timetableRepository.save(timetable);
    }

    public List<Timetable> getTimetablesByTeacherName(String name) {
        List<Timetable> timetables = new ArrayList<>();
        User user = userManagerService.getUser(name);
        List<Teaching> teachings = teachingManagerService.getTeachingsByUser(user);
        for (Teaching teaching : teachings) {
            List<Timetable> timetables1 = timetableRepository.findByTeaching(teaching);
            for (Timetable timeable1 : timetables1) {
                timetables.add(timeable1);
            }

        }
        return timetables;
    }
}
