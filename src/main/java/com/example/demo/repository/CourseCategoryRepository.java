package com.example.demo.repository;

import com.example.demo.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {

    boolean existsByName(String name);


}
