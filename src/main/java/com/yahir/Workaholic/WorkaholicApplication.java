package com.yahir.Workaholic;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Company.Company;
import com.yahir.Workaholic.Company.CompanyRepository;
import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Postings.PostingsRepository;
import com.yahir.Workaholic.Workers.Worker;
import com.yahir.Workaholic.Workers.WorkerRepository;
import com.yahir.Workaholic.Workers.Storage.StorageProperties;
import com.yahir.Workaholic.Workers.Storage.StorageService;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("api/v1/")
@EnableConfigurationProperties(StorageProperties.class)
public class WorkaholicApplication {

	private final PostingsRepository postingRepository;
	private final CompanyRepository companyRepository;
	private final WorkerRepository workerRepository;

	public WorkaholicApplication(PostingsRepository postingRepository, CompanyRepository companyRepository, WorkerRepository workerRepository) {
		this.postingRepository = postingRepository;
		this.companyRepository = companyRepository;
		this.workerRepository = workerRepository;
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

	@GetMapping("/worker/all")
	List<Worker> Test4() {
		return workerRepository.findAll();
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
