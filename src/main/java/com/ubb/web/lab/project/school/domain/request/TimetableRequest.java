package com.ubb.web.lab.project.school.domain.request;

import java.util.List;

import lombok.Data;

@Data
public class TimetableRequest {

    private String name;
    private List<String> monday;
    private List<String> tuesday;
    private List<String> wednesday;
    private List<String> thursday;
    private List<String> friday;

}
