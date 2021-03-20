package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCategory;
import com.example.demo.payload.CourseDto;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.CourseCategoryRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    CourseRepository courseRepository;
    CompanyRepository companyRepository;
    CourseCategoryRepository categoryRepository;

    public CourseService(CourseRepository courseRepository,
                         CompanyRepository companyRepository,
                         CourseCategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
    }

    public String deleteCourse(Integer id) {

        courseRepository.deleteById(id);
        return "Deleted";
    }

    public String editCourse(Integer id, CourseDto courseDto) {
        final Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()) {
            return "Course not found!";
        }
        final Course course = optionalCourse.get();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());

        final Optional<Company> optionalCompany = companyRepository.findById(courseDto.getCompanyId());
        if (!optionalCompany.isPresent()) {
            return "Company not found!";
        }

        final Optional<CourseCategory> optionalCourseCategory = categoryRepository.findById(courseDto.getCategoryId());
        if (!optionalCourseCategory.isPresent()) {
            return "Course category not found!";
        }

        course.setCourseCategory(optionalCourseCategory.get());
        course.setCompany(optionalCompany.get());

        return "Edited";
    }

    public String addCourse(CourseDto courseDto) {
        final Optional<Company> optionalCompany = companyRepository.findById(courseDto.getCompanyId());
        if (!optionalCompany.isPresent()) {
            return "Company not found!";
        }

        final boolean isExists = courseRepository.existsByNameAndCompany(courseDto.getName(), optionalCompany.get());
        if (isExists) {
            return "Course already exists in company " + optionalCompany.get().getName();
        }

        Course course = new Course();

        final Optional<CourseCategory> optionalCourseCategory = categoryRepository.findById(courseDto.getCategoryId());
        if (!optionalCourseCategory.isPresent()) {
            return "Course category not found!";
        }

        course.setCourseCategory(optionalCourseCategory.get());
        course.setCompany(optionalCompany.get());
        course.setDescription(courseDto.getDescription());
        course.setName(courseDto.getName());
        courseRepository.save(course);
        return "Course category saved";

    }

    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) {
        final Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }

}
