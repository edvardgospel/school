package com.ubb.web.lab.project.school.configuration;

import com.ubb.web.lab.project.school.dto.*;
import com.ubb.web.lab.project.school.service.SubjectManagerService;
import com.ubb.web.lab.project.school.service.SubjectValidatorService;
import com.ubb.web.lab.project.school.service.UserManagerService;
import com.ubb.web.lab.project.school.service.UserValidatorService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.ubb.web.lab.project.school.domain.entity")
@EnableJpaRepositories
public class ApplicationConfig {
    @Bean
    public UserValidatorService createUserValidatorService() {
        return new UserValidatorService();
    }

    @Bean
    public RoleEntityToRoleTransformer createRoleEntityToRoleTransformer() {
        return new RoleEntityToRoleTransformer();
    }

    @Bean
    public UserManagerService createUserManagerService() {
        return new UserManagerService();
    }

    @Bean
    public UserEntityToUserTransformer createUserEntityToUserTransformer() {
        return new UserEntityToUserTransformer();
    }

    @Bean
    public SubjectEntityToSubjectTransformer createSubjectEntityToSubjectTransformer() {
        return new SubjectEntityToSubjectTransformer();
    }

    @Bean
    public TeachingEntityToTeachingTransformer createTeachingEntityToTeachingTransformer() {
        return new TeachingEntityToTeachingTransformer();
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
    public NewUserRequestToUserEntityTransformer createNewUserRequestToUserEntityTransformer() {
        return new NewUserRequestToUserEntityTransformer();
    }
}
