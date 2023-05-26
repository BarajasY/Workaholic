package com.yahir.Workaholic.Users;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Countries.Country;
import com.yahir.Workaholic.Countries.CountryRepo;
import com.yahir.Workaholic.JobApplications.JobApplications;
import com.yahir.Workaholic.JobApplications.JobApplicationsRepository;
import com.yahir.Workaholic.Resume.ResumeRepository;
import com.yahir.Workaholic.Roles.Role;
import com.yahir.Workaholic.Roles.RoleRepository;
import com.yahir.Workaholic.UploadResume.UploadResumeController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user/")
public class UserController {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final ResumeRepository resumeRepository;
    private final CountryRepo countryRepo;
    private final JobApplicationsRepository jobApplicationsRepository;
    private final UploadResumeController uploadResumeController;

    @Autowired
    public UserController(UserRepository repository, RoleRepository roleRepository, ResumeRepository resumeRepository,
            CountryRepo countryRepo, JobApplicationsRepository jobApplicationsRepository, UploadResumeController uploadResumeController) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.resumeRepository = resumeRepository;
        this.countryRepo = countryRepo;
        this.jobApplicationsRepository = jobApplicationsRepository;
        this.uploadResumeController = uploadResumeController;
    }

    record newUserRequest(
            String name,
            String email,
            String country,
            String password,
            String role) {
    }

    @PostMapping("/register")
    private Object registerUser(@RequestBody newUserRequest request) {
        if (repository.existsByEmail(request.email())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User user = new User();
        Country country = countryRepo.findByName(request.country());
        Role role = roleRepository.findByName(request.role());
        user.setName(request.name());
        user.setEmail(request.email());
        user.setCountry(country);
        user.setPassword(request.password());
        user.setRole(role);
        String cvPath = "uploads/" + request.email();
        if (request.role().equals("company")) {
            user.setCvPath("null");
        } else {
            user.setCvPath(cvPath);
        }
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    record newAdminRegisterRequest(
            String Name,
            String Email,
            String Password,
            String Country) {
    }

    @PostMapping("/admin")
    private Object registerAdmin(@RequestBody newAdminRegisterRequest request) {
        if (repository.existsByEmail(request.Email())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Country country = countryRepo.findByName(request.Country());
            Role role = roleRepository.findByName("admin");
            User admin = new User();
            admin.setEmail(request.Email());
            admin.setCvPath("null");
            admin.setCountry(country);
            admin.setName(request.Name());
            admin.setRole(role);
            admin.setPassword(request.Password());
            repository.save(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public List<User> showUsers() {
        return repository.findAll();
    }

    record newUserLoginRequest(
            String email,
            String password) {
    }

    @PostMapping("/login")
    public Object userLogin(@RequestBody newUserLoginRequest request) {
        if (repository.existsByEmail(request.email())) {
            User account1 = repository.findByEmail(request.email());
            if (account1.getPassword().equals(request.password())) {
                return account1;
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    record newUserDeleteRequest(
        Integer user_id,
        String email
    ) {}

    @PostMapping("/delete/")
    public Object userDelete(@RequestBody newUserDeleteRequest request) {
        jobApplicationsRepository.deleteAllByUserId(request.user_id());
        User user = repository.findUserById(request.user_id());
        uploadResumeController.deleteFile(request.email());
        repository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
