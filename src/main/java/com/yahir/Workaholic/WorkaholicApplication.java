package com.yahir.Workaholic;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Company.Company;
import com.yahir.Workaholic.Company.CompanyRepository;
import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Postings.PostingsRepository;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/")
public class WorkaholicApplication {

	private final PostingsRepository postingRepository;
	private final CompanyRepository companyRepository;

	public WorkaholicApplication(PostingsRepository postingRepository, CompanyRepository companyRepository) {
		this.postingRepository = postingRepository;
		this.companyRepository = companyRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WorkaholicApplication.class, args);
	}

	@GetMapping
	String Testing() {
		return "Hola";
	}

	@GetMapping("/postings/all")
	List<Postings> Test2() {
		return postingRepository.findAll();
	}

	@GetMapping("/company/all")
	List<Company> Test3() {
		return companyRepository.findAll();
	}

}
