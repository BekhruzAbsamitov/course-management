package com.example.demo.payload;

import lombok.Data;

@Data
public class CourseCategoryDto {

    private String name;
    private String description;
    private Integer parentId;
}
