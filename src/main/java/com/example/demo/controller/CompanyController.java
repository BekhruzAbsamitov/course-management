package com.example.demo.controller;

import com.example.demo.payload.CompanyDto;
import com.example.demo.payload.Result;
import com.example.demo.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
