package com.example.demo.controller;


import com.example.demo.entity.Role;
import com.example.demo.payload.Result;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping
    public Result addRole(@RequestBody Role role) {

        Result result = roleService.add(role);
        return result;
    }


    @GetMapping
    public List<Role> getRoleList() {

        List<Role> roleList = roleService.get();
        return roleList;
    }


    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id) {

        Role byId = roleService.getById(id);
        return byId;
    }


    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {

        Result result = roleService.delete(id);
        return result;
    }


    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody Role role) {

        Result result = roleService.edit(id,role);
        return result;
    }
}
