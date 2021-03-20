package com.example.demo.service;

import com.example.demo.entity.ActiveCourse;
import com.example.demo.entity.Application;
import com.example.demo.entity.User;
import com.example.demo.payload.ApplicationDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.ActiveCourseRepository;
import com.example.demo.repository.ApplicationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    ApplicationRepository applicationRepository;
    UserRepository userRepository;
    ActiveCourseRepository activeCourseRepository;


    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, ActiveCourseRepository activeCourseRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.activeCourseRepository = activeCourseRepository;
    }

    public Result editApplication(Integer id, ApplicationDto applicationDto) {
        final Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (!optionalApplication.isPresent()) {
            return new Result("Application not found", false);
        }
        final Application application = optionalApplication.get();

        application.setMessage(applicationDto.getMessage());
        application.setStatus(applicationDto.getStatus());

        final Optional<User> repository = userRepository.findById(applicationDto.getUserId());
        if (!repository.isPresent()) {
            return new Result("User id not found", false);
        }
        final Optional<ActiveCourse> activeCourse = activeCourseRepository.findById(applicationDto.getActiveCourseId());
        if (!activeCourse.isPresent()) {
            return new Result("Active Course id not found", false);
        }
        final boolean course = applicationRepository.existsByUserAndActiveCourse(repository.get(), activeCourse.get());
        if (course) {
            return new Result("User already in active course", false);
        }
        application.setActiveCourse(activeCourse.get());
        application.setUser(repository.get());

        try {
            applicationRepository.save(application);
        } catch (Exception e) {
            return new Result("Error in editing", false);
        }
        return new Result("Application edited!", true);
    }

    public Result deleteApplication(Integer id) {
        final boolean isExists = applicationRepository.existsById(id);

        if (isExists) {
            applicationRepository.deleteById(id);
            return new Result("Application deleted", true);
        }
        return new Result("Application not found", false);
    }

    public Result addApplication(ApplicationDto applicationDto) {
        Application application = new Application();

        application.setMessage(applicationDto.getMessage());
        application.setStatus(applicationDto.getStatus());

        final Optional<User> repository = userRepository.findById(applicationDto.getUserId());
        if (!repository.isPresent()) {
            return new Result("User id not found", false);
        }
        final Optional<ActiveCourse> activeCourse = activeCourseRepository.findById(applicationDto.getActiveCourseId());
        if (!activeCourse.isPresent()) {
            return new Result("Active Course id not found", false);
        }
        final boolean course = applicationRepository.existsByUserAndActiveCourse(repository.get(), activeCourse.get());
        if (course) {
            return new Result("User already in active course", false);
        }
        application.setActiveCourse(activeCourse.get());
        application.setUser(repository.get());

        try {
            applicationRepository.save(application);
        } catch (Exception e) {
            return new Result("Error in adding", false);
        }
        return new Result("Application added!", true);
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
