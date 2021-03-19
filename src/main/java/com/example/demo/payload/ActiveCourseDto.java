package com.example.demo.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class ActiveCourseDto {

    private Integer maxStudents;
    private Integer currentStudents;
    private Date date;
    private String description;
    private Boolean status;

    private Integer courseId;
}
