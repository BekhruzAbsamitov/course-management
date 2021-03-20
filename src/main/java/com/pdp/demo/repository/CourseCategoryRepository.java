package com.pdp.demo.repository;

import com.pdp.demo.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {

    boolean existsByName(String name);


}
