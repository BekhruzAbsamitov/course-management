package com.example.demo.controller;

import com.example.demo.entity.Application;
import com.example.demo.payload.ApplicationDto;
import com.example.demo.payload.Result;
import com.example.demo.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @DeleteMapping("/{id}")
    public Result deleteApplication(@PathVariable Integer id) {
        return applicationService.deleteApplication(id);
    }

    @PutMapping("/{id}")
    public Result editApplication(@PathVariable Integer id, @RequestBody ApplicationDto applicationDto) {
        return applicationService.editApplication(id, applicationDto);
    }

    @PostMapping
    public Result addApplication(ApplicationDto applicationDto) {
        return applicationService.addApplication(applicationDto);
    }

    @GetMapping
    public List<Application> getApplicationList() {
        return applicationService.getApplicationList();
    }

    @GetMapping("/{id}")
    public Application getApplication(@PathVariable Integer id) {
        return applicationService.getApplicationById(id);
    }
}
