package com.yahir.Workaholic.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

    Company findById(Number id);

    Boolean existsByEmail(String email);

    Company findByEmail(String email);
}
