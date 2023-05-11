package com.yahir.Workaholic.Countries;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Integer>{
    
    Country findByName(String name);
}
