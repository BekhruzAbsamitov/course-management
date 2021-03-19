package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.payload.CourseDto;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public String addCourse(@RequestBody CourseDto courseDto) {
        return courseService.addCourse(courseDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }

    @PutMapping("/{id}")
    public String editCourse(@PathVariable Integer id, @RequestBody CourseDto courseDto) {
        return courseService.editCourse(id, courseDto);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<Course> getCourseList() {
        return courseService.getCourseList();
    }
}
