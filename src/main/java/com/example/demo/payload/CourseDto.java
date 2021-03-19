package com.example.demo.payload;

import lombok.Data;

@Data
public class CourseDto {

    private String name;
    private String description;
    private Integer companyId;
    private Integer categoryId;
}
