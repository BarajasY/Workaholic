package com.yahir.Workaholic.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/company")
@CrossOrigin
public class CompanyController {

    private final CompanyRepository repository;

    public CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    record newCompanyRequest(
        String name,
        String location,
        String owner,
        String email,
        String password,
        String country,
        String role,
        String tags
    ){}

    @PostMapping("register")
    public Object registerCompany(@RequestBody newCompanyRequest request) {
        if(repository.existsByEmail(request.email())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Company company = new Company();
        company.setName(request.name());
        company.setLocation(request.location());
        company.setOwner(request.owner());
        company.setEmail(request.email());
        company.setPassword(request.password());
        company.setCountry(request.country());
        company.setTags(request.tags());
        company.setRole(request.role());
        repository.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
