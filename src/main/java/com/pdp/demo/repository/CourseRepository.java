package com.pdp.demo.repository;

import com.pdp.demo.entity.Company;
import com.pdp.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    boolean existsByNameAndCompany(String name, Company company);
}
