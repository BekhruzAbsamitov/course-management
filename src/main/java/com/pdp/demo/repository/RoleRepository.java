package com.pdp.demo.repository;

import com.pdp.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository
        extends JpaRepository<Role, Integer> {
}
