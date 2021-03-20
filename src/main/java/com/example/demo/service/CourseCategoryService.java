package com.example.demo.service;

import com.example.demo.entity.CourseCategory;
import com.example.demo.payload.CourseCategoryDto;
import com.example.demo.repository.CourseCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseCategoryService {

    CourseCategoryRepository categoryRepository;

    public CourseCategoryService(CourseCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String deleteCourseCategory(Integer id) {
        categoryRepository.deleteById(id);
        return "Course category deleted";
    }

    public String editCategory(Integer id, CourseCategoryDto categoryDto) {
        final Optional<CourseCategory> courseCategory = categoryRepository.findById(id);
        if (!courseCategory.isPresent()) {
            return "Category not found";
        }
        final CourseCategory category = courseCategory.get();
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());
        if (categoryDto.getParentId() != null) {
            final Optional<CourseCategory>
                    courseCategoryOptional = categoryRepository.findById(categoryDto.getParentId());
            if (!courseCategoryOptional.isPresent()) {
                return "Parent id not found";
            }
            category.setParentId(courseCategoryOptional.get());
        }
        final boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) {
            return "Category name already exists";
        }
        categoryRepository.save(category);
        return "Category edited";
    }

    public String addCourseCategory(CourseCategoryDto categoryDto) {
        CourseCategory category = new CourseCategory();
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());
        if (categoryDto.getParentId() != null) {
            final Optional<CourseCategory>
                    courseCategoryOptional = categoryRepository.findById(categoryDto.getParentId());
            if (!courseCategoryOptional.isPresent()) {
                return "Parent id not found";
            }
            category.setParentId(courseCategoryOptional.get());
        }
        final boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) {
            return "Category name already exists";
        }
        categoryRepository.save(category);
        return "Saved!";
    }

    public List<CourseCategory> getCourseCategoryList() {
        return categoryRepository.findAll();
    }

    public CourseCategory getCourseCategoryById(Integer id) {
        final Optional<CourseCategory> optionalCourseCategory = categoryRepository.findById(id);
        if (!optionalCourseCategory.isPresent()) {
            return null;
        }
        return optionalCourseCategory.get();
    }

}
