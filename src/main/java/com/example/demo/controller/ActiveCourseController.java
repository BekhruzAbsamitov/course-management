package com.example.demo.controller;

import com.example.demo.entity.ActiveCourse;
import com.example.demo.payload.ActiveCourseDto;
import com.example.demo.service.ActiveCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ActiveCourseController {

    ActiveCourseService activeCourseService;

    public ActiveCourseController(ActiveCourseService activeCourseService) {
        this.activeCourseService = activeCourseService;
    }

    @PostMapping
    public String addActiveCourse(@RequestBody ActiveCourseDto activeCourseDto) {
        return activeCourseService.addActiveCourse(activeCourseDto);
    }

    @GetMapping("/{id}")
    public ActiveCourse getActiveCourseBuId(@PathVariable Integer id) {
        return activeCourseService.getActiveCourseById(id);
    }

    @GetMapping
    public List<ActiveCourse> getActiveCourseList() {
        return activeCourseService.getActiveCourseList();
    }

    @PutMapping("/{id}")
    public String editActiveCourse(@PathVariable Integer id, @RequestBody ActiveCourseDto activeCourseDto) {
        return activeCourseService.editActiveCourse(id, activeCourseDto);
    }

    @DeleteMapping("/{id}")
    public String deleteActiveCourse(@PathVariable Integer id) {
        return activeCourseService.deleteActiveCourse(id);
    }
}
