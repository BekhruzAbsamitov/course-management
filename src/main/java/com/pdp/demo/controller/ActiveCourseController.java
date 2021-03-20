package com.pdp.demo.controller;

import com.pdp.demo.entity.ActiveCourse;
import com.pdp.demo.payload.ActiveCourseDto;
import com.pdp.demo.service.ActiveCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activeCourse")
public class ActiveCourseController {



    //Toshkent dagi course si java bo'lgan company ni << address i ,contact >>

   final ActiveCourseService activeCourseService;

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
