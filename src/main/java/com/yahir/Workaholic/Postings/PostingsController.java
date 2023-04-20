package com.yahir.Workaholic.Postings;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Company.Company;
import com.yahir.Workaholic.Company.CompanyRepository;

@RestController
@RequestMapping("api/v1/postings")
@CrossOrigin
public class PostingsController {
    
    private final PostingsRepository repository;
    private final CompanyRepository companyRepository;

    public PostingsController(PostingsRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    record newPostingRequest(
        String businessName,
        String title,
        String description,
        String[] jobType,
        Number salary,
        String salaryCurrency,
        String salaryRate,
        String location,
        String country,
        String duration,
        String date,
        String[] tags,
        String[] benefits,
        Number company_id
    ){}

    @PostMapping("/add")
    public Object addJobPosting(@RequestBody newPostingRequest request) {
        Postings Posting = new Postings();
        Company company = companyRepository.findById(request.company_id());
        Posting.setBusinessName(request.businessName());
        Posting.setTitle(request.title());
        Posting.setDescription(request.description());
        Posting.setJobType(request.jobType());
        Posting.setSalary(request.salary());
        Posting.setSalaryCurrency(request.salaryCurrency());
        Posting.setSalaryRate(request.salaryRate());
        Posting.setLocation(request.location());
        Posting.setCountry(request.country());
        Posting.setDuration(request.duration());
        Posting.setDate(request.date());
        Posting.setTags(request.tags());
        Posting.setBenefits(request.benefits());
        Posting.setCompany(company);
        repository.save(Posting);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object showJobPostPerId(@PathVariable Number id) {
        Postings post = repository.findPostingsById(id);
        if(post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return post;
    }

    @GetMapping("all")
    public List<Postings> showAll() {
        return repository.findAll();
    }
}
