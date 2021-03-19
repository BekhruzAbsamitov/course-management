package com.example.demo.controller;

import com.example.demo.service.ApplicationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
}
