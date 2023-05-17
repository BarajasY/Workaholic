package com.yahir.Workaholic.JobApplications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Postings.PostingsRepository;
import com.yahir.Workaholic.Users.User;
import com.yahir.Workaholic.Users.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/jobapplication/")
public class JobApplicationsController {
    
    @Autowired
    private JobApplicationsRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostingsRepository postingsRepository;

    record newJobAppRequest(
        Number UserId,
        Number PostingId,
        String coverLetter
    ){}

    @PostMapping("/add")
    public Object addJobApplication(@RequestBody newJobAppRequest request) {
        User test = userRepository.findUserById(request.UserId());
        Postings test3 = postingsRepository.findPostingsById(request.PostingId());
        JobApplications jobApplications = new JobApplications();
        jobApplications.user(test);
        jobApplications.posting(test3);
        jobApplications.coverLetter(request.coverLetter());
        repository.save(jobApplications);
        System.out.println("Hola");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<JobApplications> allJobApplications() {
        return repository.findAll();
    }

    @GetMapping("/verify/{UserId}/{PostingId}")
    public Object verifyJobApplication(@PathVariable Number UserId, @PathVariable Number PostingId) {
        if(repository.existsByUserIdAndPostingId(UserId, PostingId)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
