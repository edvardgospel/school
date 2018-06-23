package com.ubb.web.lab.project.school.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ubb.web.lab.project.school.service.SubjectManagerService;
import com.ubb.web.lab.project.school.service.SubjectValidatorService;
import com.ubb.web.lab.project.school.service.TimetableManagerService;
import com.ubb.web.lab.project.school.service.UserManagerService;

@Configuration
@EntityScan("com.ubb.web.lab.project.school.domain.entity")
@EnableJpaRepositories
public class ApplicationConfig {

    @Bean
    public UserManagerService createUserManagerService() {
        return new UserManagerService();
    }

    @Bean
    public SubjectManagerService createSubjectManagerService() {
        return new SubjectManagerService();
    }

    @Bean
    public SubjectValidatorService createSubjectValidatorService() {
        return new SubjectValidatorService();
    }

    @Bean
    public TimetableManagerService createTimetableManagerService() {
        return new TimetableManagerService();
    }
}
