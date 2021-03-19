package com.example.demo.service;

import com.example.demo.entity.CompanyCategory;
import com.example.demo.payload.Result;
import com.example.demo.repository.CategoryForCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryForCompanyService {

    final CategoryForCompanyRepository categoryForCompanyRepository;

    public CategoryForCompanyService(CategoryForCompanyRepository categoryForCompanyRepository) {
        this.categoryForCompanyRepository = categoryForCompanyRepository;
    }


    public Result add(CompanyCategory companyCategory) {

        CompanyCategory newCompanyCategory = new CompanyCategory();
        newCompanyCategory.setName(companyCategory.getName());
        newCompanyCategory.setDescription(companyCategory.getDescription());

        categoryForCompanyRepository.save(newCompanyCategory);
        return new Result("Company category added", true);
    }

    public List<CompanyCategory> get() {

        List<CompanyCategory> all = categoryForCompanyRepository.findAll();
        return all;
    }

    public CompanyCategory getById(Integer id) {

        Optional<CompanyCategory> optionalCompanyCategory = categoryForCompanyRepository.findById(id);
        return optionalCompanyCategory.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = categoryForCompanyRepository.existsById(id);
        if (exists) {
            categoryForCompanyRepository.deleteById(id);
            return new Result("Company category deleted", true);
        }
        return new Result("Such company category not found", false);
    }

    public Result edit(Integer id, CompanyCategory companyCategory) {

        Optional<CompanyCategory> optionalCompanyCategory = categoryForCompanyRepository.findById(id);
        if (optionalCompanyCategory.isPresent()) {

            CompanyCategory editing = optionalCompanyCategory.get();
            editing.setName(companyCategory.getName());
            editing.setDescription(companyCategory.getDescription());

            categoryForCompanyRepository.save(editing);
            return new Result("Company category edited", true);
        }
        return new Result("Such company category not found", false);
    }
}
