package com.example.demo.service;


import com.example.demo.entity.Role;
import com.example.demo.payload.Result;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Result add(Role role) {

        Role newRole = new Role();
        role.setName(role.getName());
        role.setDescription(role.getDescription());

        roleRepository.save(newRole);
        return new Result("Role added", true);
    }

    public List<Role> get() {

        return roleRepository.findAll();

    }

    public Role getById(Integer id) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = roleRepository.existsById(id);
        if (exists) {
            roleRepository.deleteById(id);
            return new Result("Role deleted", true);
        }
        return new Result("Such role not found", false);
    }

    public Result edit(Integer id, Role role) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role editingRole = optionalRole.get();
            editingRole.setName(role.getName());
            editingRole.setDescription(role.getDescription());

            roleRepository.save(editingRole);
            return new Result("Role edited", true);
        }
        return new Result("Such role not foud", false);
    }
}
