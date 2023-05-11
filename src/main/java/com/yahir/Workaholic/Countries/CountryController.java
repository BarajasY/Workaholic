package com.yahir.Workaholic.Countries;

import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/country")
@CrossOrigin
public class CountryController {
    
    private final CountryRepo repo;

    public CountryController(CountryRepo repo) {
        this.repo = repo;
    }

    @GetMapping("")
    public List<Country> getCountries() {
        return repo.findAll();
    }

}
