package com.example.demo.controller;

import com.example.demo.entity.CourseCategory;
import com.example.demo.payload.CourseCategoryDto;
import com.example.demo.service.CourseCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseCategoryController {

    CourseCategoryService courseCategoryService;

    public CourseCategoryController(CourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @PutMapping("/{id}")
    public String editCourseCategory(@PathVariable Integer id, @RequestBody CourseCategoryDto categoryDto) {
        return courseCategoryService.editCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCourseCategory(@PathVariable Integer id) {
        return courseCategoryService.deleteCourseCategory(id);
    }

    @PostMapping
    public String addCourseCategory(CourseCategoryDto categoryDto) {
        return courseCategoryService.addCourseCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public CourseCategory getCourseCategoryById(@PathVariable Integer id) {
        return courseCategoryService.getCourseCategoryById(id);
    }

    @GetMapping
    public List<CourseCategory> getCourseCategoryList() {
        return courseCategoryService.getCourseCategoryList();
    }
}
