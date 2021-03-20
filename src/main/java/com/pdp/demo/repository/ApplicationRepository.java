package com.pdp.demo.repository;

import com.pdp.demo.entity.ActiveCourse;
import com.pdp.demo.entity.Application;
import com.pdp.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    boolean existsByUserAndActiveCourse(User user, ActiveCourse activeCourse);
}
