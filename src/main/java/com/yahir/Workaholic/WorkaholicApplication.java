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
import com.yahir.Workaholic.JobTypes.JobType;
import com.yahir.Workaholic.JobTypes.JobTypeRepository;
import com.yahir.Workaholic.Postings.PostingsRepository;
import com.yahir.Workaholic.Rates.RateRepository;
import com.yahir.Workaholic.Resume.ResumeRepository;
import com.yahir.Workaholic.Roles.Role;
import com.yahir.Workaholic.Roles.RoleRepository;
import com.yahir.Workaholic.Tags.Tag;
import com.yahir.Workaholic.Tags.TagRepository;
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
	private TagRepository tagRepository;

	public WorkaholicApplication(FileStorageService storageService, UploadResumeRepository uploadResumeRepository,
			PostingsRepository postingRepository, JobApplicationsRepository jobApplicationsRepository,
			UserRepository userRepository, RoleRepository roleRepository, JobTypeRepository jobTypeRepository,
			RateRepository rateRepository, CurrencyRepository currencyRepository, ResumeRepository resumeRepository,
			TagRepository tagRepository) {
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
		this.tagRepository = tagRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkaholicApplication.class, args);
	}

	record NewUserLoginRequest(
			String Email,
			String Password) {
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}

	@Bean
	public CommandLineRunner createRoles(RoleRepository repository) {
		return (args) -> {
			if (repository.existsByName("worker")) {
			} else {
				repository.save(new Role(1, "worker"));
				repository.save(new Role(2, "admin"));
				repository.save(new Role(3, "company"));
			}
		};
	}

	@Bean
	public CommandLineRunner createJobTypes(JobTypeRepository repository) {
		return (args) -> {
			if (repository.existsByType("Full-Time")) {
			} else {
				repository.save(new JobType(1, "Full-Time"));
				repository.save(new JobType(1, "Part-Time"));
				repository.save(new JobType(1, "Freelance"));
			}
		};
	}

	@Bean
	public CommandLineRunner createTags(TagRepository repository) {
		return (args) -> {
			if (repository.existsByTagName("Software")) {
			} else {
				repository.save(new Tag(null, "Software"));
				repository.save(new Tag(null, "Cybersecurity"));
				repository.save(new Tag(null, "Accuntancy"));
				repository.save(new Tag(null, "Law"));
				repository.save(new Tag(null, "Human Resources"));
				repository.save(new Tag(null, "Marketing"));
				repository.save(new Tag(null, "Medicine"));
				repository.save(new Tag(null, "Nursery"));
				repository.save(new Tag(null, "Teaching"));
				repository.save(new Tag(null, "Assistant"));
				repository.save(new Tag(null, "Customer Service"));
				repository.save(new Tag(null, "Data"));
				repository.save(new Tag(null, "UI/UX"));
				repository.save(new Tag(null, "Sales"));
				repository.save(new Tag(null, "Secretary"));
			}
		};
	}
}
