package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.payload.Result;
import com.example.demo.payload.UserDto;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Result addUser(@RequestBody UserDto userdto) {
        return userService.add(userdto);
    }


    @GetMapping
    public List<User> userEntityList() {
        return userService.get();
    }


    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }
}
