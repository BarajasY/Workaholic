package com.yahir.Workaholic.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Resume.Resume;
import com.yahir.Workaholic.Resume.ResumeRepository;
import com.yahir.Workaholic.Roles.Role;
import com.yahir.Workaholic.Roles.RoleRepository;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user/")
public class UserController {
    
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final ResumeRepository resumeRepository;

    @Autowired
    public UserController(UserRepository repository, RoleRepository roleRepository, ResumeRepository resumeRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.resumeRepository = resumeRepository;
    }

    record newUserRequest(
        String name,
        String email,
        String country,
        String password,
        String role,
        String cvPath
    ){}

    @PostMapping("/register")
    private Object registerUser(@RequestBody newUserRequest request) {
        if(repository.existsByEmail(request.email())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User user = new User();
        Role role = roleRepository.findByName(request.role());
        user.setName(request.name());
        user.setEmail(request.email());
        user.setCountry(request.country());
        user.setPassword(request.password());
        user.setRole(role);
        user.setCvPath(request.cvPath());
        repository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hola")
    private String test() {
        return "Hola";
    }

    @GetMapping("/all")
    public List<User> showUsers() {
        return repository.findAll();
    }
}
