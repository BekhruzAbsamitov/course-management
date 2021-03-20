package com.pdp.demo.controller;

import com.pdp.demo.entity.Course;
import com.pdp.demo.payload.CourseDto;
import com.pdp.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
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
