package com.example.demo.controller;


import com.example.demo.entity.CompanyCategory;
import com.example.demo.payload.Result;
import com.example.demo.service.CategoryForCompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoryForCompany")
public class CategoryForCompanyController {

    final CategoryForCompanyService categoryForCompanyService;

    public CategoryForCompanyController(CategoryForCompanyService categoryForCompanyService) {
        this.categoryForCompanyService = categoryForCompanyService;
    }


    @PostMapping
    public Result add(@RequestBody CompanyCategory companyCategory) {

        Result result = categoryForCompanyService.add(companyCategory);
        return result;
    }

    @GetMapping
    public List<CompanyCategory> getCompanyCategoryList() {

        List<CompanyCategory> companyCategories = categoryForCompanyService.get();
        return companyCategories;
    }


    @GetMapping("/{id}")
    public CompanyCategory getById(@PathVariable Integer id) {

        CompanyCategory byId = categoryForCompanyService.getById(id);
        return byId;
    }


    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        Result result = categoryForCompanyService.delete(id);
        return result;
    }


    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody CompanyCategory companyCategory) {

        Result result = categoryForCompanyService.edit(id, companyCategory);
        return result;
    }
}
