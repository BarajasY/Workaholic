package com.yahir.Workaholic.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Roles.Role;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }
}
