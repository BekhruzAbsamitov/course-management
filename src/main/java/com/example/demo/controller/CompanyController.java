package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.payload.CompanyDto;
import com.example.demo.payload.Result;
import com.example.demo.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping
    public Result addCompany(@RequestBody CompanyDto companydto) {

        Result result = companyService.add(companydto);
        return result;
    }


    @GetMapping
    public List<Company> getCompanies() {

        return companyService.get();
    }


    @GetMapping("/{id}")
    public Company getById(@PathVariable Integer id) {

        return companyService.getById(id);
    }


    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        return companyService.delete(id);
    }


    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id,@RequestBody CompanyDto companyDto){

       return companyService.edit(id,companyDto);
    }
}
