package com.yahir.Workaholic;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Company.Company;
import com.yahir.Workaholic.Company.CompanyRepository;
import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Postings.PostingsRepository;
import com.yahir.Workaholic.UploadResume.UploadResumeRepository;
import com.yahir.Workaholic.UploadResume.UploadService.FileStorageService;
import com.yahir.Workaholic.Workers.Worker;
import com.yahir.Workaholic.Workers.WorkerRepository;

import jakarta.annotation.Resource;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class WorkaholicApplication implements CommandLineRunner {

	@Resource
	FileStorageService storageService;

	private final UploadResumeRepository uploadResumeRepository;
	private final PostingsRepository postingRepository;
	private final CompanyRepository companyRepository;
	private final WorkerRepository workerRepository;

	public WorkaholicApplication(FileStorageService storageService, UploadResumeRepository uploadResumeRepository, PostingsRepository postingRepository, CompanyRepository companyRepository, WorkerRepository workerRepository) {
		this.storageService = storageService;
		this.uploadResumeRepository = uploadResumeRepository;
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

	record NewUserLoginRequest(
		String Email,
		String Password
	){}

	@PostMapping("/login")
	public Object userLogin(@RequestBody NewUserLoginRequest request) {
		Worker dbWorker = workerRepository.findWorkerByEmail(request.Email());
		if(dbWorker == null) {
			Company dbCompany = companyRepository.findByEmail(request.Email());
			if(dbCompany.getPassword().equals(request.Password())) {
				return dbCompany;
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} else {
			if(dbWorker.getPassword().equals(request.Password())) {
				return dbWorker;
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
	}	

	@Override
	public void run(String ...arg) throws Exception {
		storageService.init();
	}
}
