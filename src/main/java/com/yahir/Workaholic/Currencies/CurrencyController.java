package com.yahir.Workaholic.Currencies;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
    
    private final CurrencyRepository repository;

    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }


}
