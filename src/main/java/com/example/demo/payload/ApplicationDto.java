package com.example.demo.payload;

import lombok.Data;

@Data
public class ApplicationDto {

    private String message;
    private Boolean status;

    private Integer userId;
    private Integer activeCourseId;
}
