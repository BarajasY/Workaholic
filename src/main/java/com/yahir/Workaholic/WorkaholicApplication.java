package com.yahir.Workaholic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Currencies.CurrencyRepository;
import com.yahir.Workaholic.JobApplications.JobApplicationsRepository;
import com.yahir.Workaholic.JobTypes.JobTypeRepository;
import com.yahir.Workaholic.Postings.PostingsRepository;
import com.yahir.Workaholic.Rates.RateRepository;
import com.yahir.Workaholic.Resume.ResumeRepository;
import com.yahir.Workaholic.Roles.Role;
import com.yahir.Workaholic.Roles.RoleRepository;
import com.yahir.Workaholic.UploadResume.UploadResumeRepository;
import com.yahir.Workaholic.UploadResume.UploadService.FileStorageService;
import com.yahir.Workaholic.Users.UserRepository;

import jakarta.annotation.Resource;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class WorkaholicApplication implements CommandLineRunner {

	@Resource
	FileStorageService storageService;

	private UploadResumeRepository uploadResumeRepository;
	private PostingsRepository postingRepository;
	private JobApplicationsRepository jobApplicationsRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private JobTypeRepository jobTypeRepository;
	private RateRepository rateRepository;
	private CurrencyRepository currencyRepository;
	private ResumeRepository resumeRepository;

	public WorkaholicApplication(FileStorageService storageService, UploadResumeRepository uploadResumeRepository, PostingsRepository postingRepository, JobApplicationsRepository jobApplicationsRepository, UserRepository userRepository, RoleRepository roleRepository, JobTypeRepository jobTypeRepository, RateRepository rateRepository, CurrencyRepository currencyRepository, ResumeRepository resumeRepository) {
		this.storageService = storageService;
		this.uploadResumeRepository = uploadResumeRepository;
		this.postingRepository = postingRepository;
		this.jobApplicationsRepository = jobApplicationsRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.jobTypeRepository = jobTypeRepository;
		this.rateRepository = rateRepository;
		this.currencyRepository = currencyRepository;
		this.resumeRepository = resumeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkaholicApplication.class, args);
	}
	record NewUserLoginRequest(
		String Email,
		String Password
	){}
	
	@Override
	public void run(String ...arg) throws Exception {
		storageService.init();
	}

	@Bean
	public CommandLineRunner createRoles(RoleRepository repository) {
		return (args)-> {
			if(repository.existsByName("worker")) {
			} else {
				repository.save(new Role(1, "worker"));
				repository.save(new Role(2, "admin"));
				repository.save(new Role(3, "company"));
			}
		};
	}
}
