package com.pdp.demo.repository;

import com.pdp.demo.entity.ActiveCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveCourseRepository extends JpaRepository<ActiveCourse, Integer> {
}
