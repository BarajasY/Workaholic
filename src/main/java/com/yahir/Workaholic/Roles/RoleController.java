package com.yahir.Workaholic.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RoleController {
    
    private final RoleRepository repository;

    @Autowired
    public RoleController(RoleRepository repository) {
        this.repository = repository;
    }

    public void createRole() {
        Boolean roleExists = repository.existsByName("worker");
        if(roleExists) {
            return;
        }
        Role role = new Role();
        Role role2 = new Role();
        Role role3 = new Role();
        role.name("worker");
        role2.name("admin");
        role3.name("company");
        repository.save(role);
        repository.save(role2);
        repository.save(role3);
    }
}
