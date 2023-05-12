package com.yahir.Workaholic.Rates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    
    Rate findRateByRateName(String rateName);
}
