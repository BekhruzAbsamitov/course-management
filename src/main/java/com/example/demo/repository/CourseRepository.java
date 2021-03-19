package com.example.demo.repository;

import com.example.demo.entity.Company;
import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    boolean existsByNameAndCompany(String name, Company company);
}
