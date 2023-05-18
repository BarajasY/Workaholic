package com.yahir.Workaholic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Countries.Country;
import com.yahir.Workaholic.Countries.CountryRepo;
import com.yahir.Workaholic.Currencies.Currency;
import com.yahir.Workaholic.Currencies.CurrencyRepository;
import com.yahir.Workaholic.JobApplications.JobApplicationsRepository;
import com.yahir.Workaholic.JobTypes.JobType;
import com.yahir.Workaholic.JobTypes.JobTypeRepository;
import com.yahir.Workaholic.Postings.PostingsRepository;
import com.yahir.Workaholic.Rates.Rate;
import com.yahir.Workaholic.Rates.RateRepository;
import com.yahir.Workaholic.Resume.ResumeRepository;
import com.yahir.Workaholic.Roles.Role;
import com.yahir.Workaholic.Roles.RoleRepository;
import com.yahir.Workaholic.Tags.Tag;
import com.yahir.Workaholic.Tags.TagRepository;
import com.yahir.Workaholic.UploadResume.UploadService.FileStorageService;
import com.yahir.Workaholic.Users.User;
import com.yahir.Workaholic.Users.UserRepository;

import jakarta.annotation.Resource;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class WorkaholicApplication implements CommandLineRunner {

	@Resource
	FileStorageService storageService;

	private PostingsRepository postingRepository;
	private JobApplicationsRepository jobApplicationsRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private JobTypeRepository jobTypeRepository;
	private RateRepository rateRepository;
	private CurrencyRepository currencyRepository;
	private ResumeRepository resumeRepository;
	private CountryRepo countryRepo;
	private TagRepository tagRepository;

	public WorkaholicApplication(FileStorageService storageService,
			PostingsRepository postingRepository, JobApplicationsRepository jobApplicationsRepository,
			UserRepository userRepository, RoleRepository roleRepository, JobTypeRepository jobTypeRepository,
			RateRepository rateRepository, CurrencyRepository currencyRepository, ResumeRepository resumeRepository,
			TagRepository tagRepository, CountryRepo countryRepo) {
		this.postingRepository = postingRepository;
		this.jobApplicationsRepository = jobApplicationsRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.jobTypeRepository = jobTypeRepository;
		this.rateRepository = rateRepository;
		this.currencyRepository = currencyRepository;
		this.resumeRepository = resumeRepository;
		this.tagRepository = tagRepository;
		this.countryRepo = countryRepo;
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
	public CommandLineRunner addCountries(CountryRepo repository) {
		return (args) -> {
			if(repository.existsById(1)) {

			} else {
				repository.save(new Country(1, "Mexico"));
				repository.save(new Country(2, "USA"));
				repository.save(new Country(3, "Canada"));
				repository.save(new Country(4, "Argentina"));
				repository.save(new Country(5, "España"));
				repository.save(new Country(6, "Francia"));
				repository.save(new Country(7, "UK"));
				repository.save(new Country(8, "Alemania"));
				repository.save(new Country(9, "Portugal"));
				repository.save(new Country(10, "Chile"));
			}
		};
	}

	
	@Bean
	public CommandLineRunner createJobTypes(JobTypeRepository repository) {
		return (args) -> {
			if (repository.existsByType("Full-Time")) {
			} else {
				repository.save(new JobType(1, "Full-Time"));
				repository.save(new JobType(2, "Part-Time"));
				repository.save(new JobType(3, "Freelance"));
			}
		};
	}
	
	@Bean
	public CommandLineRunner addSalaryRates(RateRepository repository) {
		return (args) -> {
			if (repository.existsById(1)) {
				
			} else {
				repository.save(new Rate(1, "hr"));
				repository.save(new Rate(2, "day"));
				repository.save(new Rate(3, "week"));
				repository.save(new Rate(4, "biweekly"));
				repository.save(new Rate(5, "month"));
				repository.save(new Rate(6, "year"));
			}
		};
	}
	
	@Bean
	public CommandLineRunner addSalaryCurrency(CurrencyRepository repository) {
		return (args) -> {
			if(repository.existsById(1)) {
				
			} else {
				repository.save(new Currency(1, "MXN"));
				repository.save(new Currency(2, "USD"));
				repository.save(new Currency(3, "CAD"));
				repository.save(new Currency(4, "EUR"));
				repository.save(new Currency(5, "ARS"));
				repository.save(new Currency(6, "CLP"));
				repository.save(new Currency(7, "GBP"));
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

/* 	@Bean
	public CommandLineRunner addAdmin(UserRepository repository) {
		return (args) -> {
			if(repository.existsById(1)) {
	
			} else {
				User admin = new User();
				Country country = countryRepo.findByName("Mexico");
				Role role = roleRepository.findByName("admin");
				admin.setName("admin");
				admin.setEmail("admin@workaholic.com");
				admin.setPassword("admin");
				admin.setCvPath(null);
				admin.setRole(role);
				admin.setRole(new Role(2, "admin"));
				admin.setCountry(new Country(1, "Mexico"));
				admin.setCountry(country);
				userRepository.save(admin);
			}
		};
	} */
}
