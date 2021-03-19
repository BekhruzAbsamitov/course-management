package com.example.demo.service;

import com.example.demo.entity.ActiveCourse;
import com.example.demo.entity.Course;
import com.example.demo.payload.ActiveCourseDto;
import com.example.demo.repository.ActiveCourseRepository;
import com.example.demo.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class ActiveCourseService {

    ActiveCourseRepository activeCourseRepository;
    CourseRepository courseRepository;

    public ActiveCourseService(ActiveCourseRepository activeCourseRepository, CourseRepository courseRepository) {
        this.activeCourseRepository = activeCourseRepository;
        this.courseRepository = courseRepository;
    }

    public String deleteActiveCourse(Integer id) {
        activeCourseRepository.deleteById(id);
        return "Active Course deleted";
    }

    public String editActiveCourse(Integer id, ActiveCourseDto activeCourseDto) {
        final Optional<ActiveCourse> optionalActiveCourse = activeCourseRepository.findById(id);
        if (optionalActiveCourse.isEmpty()) {
            return "Active course not found";
        }
        final ActiveCourse activeCourse = optionalActiveCourse.get();
        activeCourse.setDescription(activeCourseDto.getDescription());
        activeCourse.setCurrentStudents(activeCourseDto.getCurrentStudents());
        activeCourse.setDate(activeCourseDto.getDate());
        activeCourse.setStatus(activeCourseDto.getStatus());

        final Optional<Course> optionalCourse = courseRepository.findById(activeCourseDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return "Course not found";
        }

        activeCourse.setCourse(optionalCourse.get());
        activeCourseRepository.save(activeCourse);
        return "Active course edited";
    }

    public String addActiveCourse(ActiveCourseDto activeCourseDto) {
        ActiveCourse activeCourse = new ActiveCourse();
        activeCourse.setDescription(activeCourseDto.getDescription());
        activeCourse.setCurrentStudents(activeCourseDto.getCurrentStudents());
        activeCourse.setDate(activeCourseDto.getDate());
        activeCourse.setStatus(activeCourseDto.getStatus());

        final Optional<Course> optionalCourse = courseRepository.findById(activeCourseDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return "Course not found";
        }

        activeCourse.setCourse(optionalCourse.get());
        activeCourseRepository.save(activeCourse);
        return "Saved";
    }

    public List<ActiveCourse> getActiveCourseList() {
        return activeCourseRepository.findAll();
    }

    public ActiveCourse getActiveCourseById(Integer id) {
        final Optional<ActiveCourse> optionalActiveCourse =
                activeCourseRepository.findById(id);

        if (optionalActiveCourse.isEmpty()) {
            return null;
        }
        return optionalActiveCourse.get();
    }

}
