package com.yahir.Workaholic.Rates;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {
    
    private final RateRepository repository;
    
    public RateController(RateRepository repository) {
        this.repository = repository;
    }
    
}