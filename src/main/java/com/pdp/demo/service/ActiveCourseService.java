package com.pdp.demo.service;

import com.pdp.demo.entity.ActiveCourse;
import com.pdp.demo.entity.Course;
import com.pdp.demo.payload.ActiveCourseDto;
import com.pdp.demo.repository.ActiveCourseRepository;
import com.pdp.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiveCourseService {

    final ActiveCourseRepository activeCourseRepository;
    final CourseRepository courseRepository;

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
        if (!optionalActiveCourse.isPresent()) {
            return "Active course not found";
        }
        final ActiveCourse activeCourse = optionalActiveCourse.get();
        activeCourse.setDescription(activeCourseDto.getDescription());
        activeCourse.setCurrentStudents(activeCourseDto.getCurrentStudents());
        activeCourse.setDate(activeCourseDto.getDate());
        activeCourse.setStatus(activeCourseDto.getStatus());

        final Optional<Course> optionalCourse = courseRepository.findById(activeCourseDto.getCourseId());
        if (!optionalCourse.isPresent()) {
            return "Course not found";
        }

        activeCourse.setCourse(optionalCourse.get());
        activeCourseRepository.save(activeCourse);
        return "Active course edited";
    }

    public String addActiveCourse(ActiveCourseDto activeCourseDto) {
        ActiveCourse activeCourse = new ActiveCourse();
        activeCourse.setDescription(activeCourseDto.getDescription());
        activeCourse.setMaxStudents(activeCourseDto.getMaxStudents());
        activeCourse.setCurrentStudents(activeCourseDto.getCurrentStudents());
        activeCourse.setDate(activeCourseDto.getDate());
        activeCourse.setStatus(activeCourseDto.getStatus());

        final Optional<Course> optionalCourse = courseRepository.findById(activeCourseDto.getCourseId());
        if (!optionalCourse.isPresent()) {
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

        if (!optionalActiveCourse.isPresent()) {
            return null;
        }
        return optionalActiveCourse.get();
    }

}
