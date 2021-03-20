package com.example.demo.service;

import com.example.demo.entity.Application;
import com.example.demo.payload.ApplicationDto;
import com.example.demo.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public String addApplication(ApplicationDto applicationDto) {
        Application application = new Application();

        application.setMessage(applicationDto.getMessage());
        application.setStatus(applicationDto.getStatus());


//        applicationRepository.existsByUserAndActiveCourse();
        return null;
    }

    public List<Application> getApplicationList() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Integer id) {
        final Optional<Application> repository = applicationRepository.findById(id);
        if (!repository.isPresent()) {
            return null;
        }
        return repository.get();
    }
}
