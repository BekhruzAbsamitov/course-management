package com.example.demo.repository;

import com.example.demo.entity.ActiveCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveCourseRepository extends JpaRepository<ActiveCourse, Integer> {
}
