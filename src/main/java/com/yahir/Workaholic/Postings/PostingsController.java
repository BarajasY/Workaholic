package com.yahir.Workaholic.Postings;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/postings")
@CrossOrigin
public class PostingsController {
    
    private final PostingsRepository repository;

    public PostingsController(PostingsRepository repository) {
        this.repository = repository;
    }

    record newPostingRequest(
        String businessName,
        String title,
        String description,
        String jobType,
        Number salary,
        String salaryCurrency,
        String salaryRate,
        String location,
        String country,
        String duration,
        Number date,
        String tags,
        String benefits
    ){}

    @PostMapping("/add")
    public Object addJobPosting(@RequestBody newPostingRequest request) {
        Postings Posting = new Postings();
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
        repository.save(Posting);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("all")
    public List<Postings> showAll() {
        return repository.findAll();
    }
}