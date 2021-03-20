package com.pdp.demo.controller;

import com.pdp.demo.entity.Company;
import com.pdp.demo.payload.CompanyDto;
import com.pdp.demo.payload.Response;
import com.pdp.demo.payload.Result;
import com.pdp.demo.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/getCompanyNameAndAddress")
    public Response getResponse(@RequestParam String regionName, @RequestParam String courseName) {
        return companyService.getResponse(regionName, courseName);
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
    public Result editById(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {

        return companyService.edit(id, companyDto);
    }
}
