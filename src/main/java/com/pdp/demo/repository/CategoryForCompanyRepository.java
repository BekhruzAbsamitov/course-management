package com.pdp.demo.repository;

import com.pdp.demo.entity.CompanyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryForCompanyRepository extends JpaRepository<CompanyCategory, Integer> {
}
