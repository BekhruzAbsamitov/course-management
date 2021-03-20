package com.pdp.demo.service;

import com.pdp.demo.entity.CourseCategory;
import com.pdp.demo.payload.CourseCategoryDto;
import com.pdp.demo.repository.CourseCategoryRepository;
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

    public String addCourseCategory( CourseCategoryDto categoryDto) {

        CourseCategory newCourse=new CourseCategory();
        newCourse.setName(categoryDto.getName());
        newCourse.setDescription(categoryDto.getDescription());

        if(categoryDto.getParentId()!=null) {
            Optional<CourseCategory> optionalCourseCategory = categoryRepository.findById(categoryDto.getParentId());
            if (optionalCourseCategory.isPresent()) {
                CourseCategory courseCategory = optionalCourseCategory.get();
                newCourse.setParentId(courseCategory);
                categoryRepository.save(newCourse);
                return "Course category saved";
            }else {
                return "Such course category not found";
            }
        }
        newCourse.setParentId(null);
        categoryRepository.save(newCourse);
        return "Course category saved without parentId";


//        CourseCategory category = new CourseCategory();
//        category.setDescription(categoryDto.getDescription());
//        category.setName(categoryDto.getName());
//        if (categoryDto.getParentId() != null) {
//            final Optional<CourseCategory>
//                    courseCategoryOptional = categoryRepository.findById(categoryDto.getParentId());
//            if (!courseCategoryOptional.isPresent()) {
//                return "Parent id not found";
//            }
//            category.setParentId(courseCategoryOptional.get());
//        }
//        if(categoryDto.getParentId().equals(null)){
//            category.setParentId(null);
//            categoryRepository.save(category);
//        }
//        final boolean exists = categoryRepository.existsByName(categoryDto.getName());
//        if (exists) {
//            return "Category name already exists";
//        }
//        categoryRepository.save(category);
//        return "Saved!";
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
