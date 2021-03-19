package com.example.demo.repository;

import com.example.demo.entity.CompanyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryForCompanyRepository extends JpaRepository<CompanyCategory, Integer> {
}
