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
import com.yahir.Workaholic.Workers.Worker;
import com.yahir.Workaholic.Workers.WorkerRepository;

@RestController
@CrossOrigin
@RequestMapping("api/v1/jobapplication")
public class JobApplicationsController {
    
    @Autowired
    private JobApplicationsRepository repository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PostingsRepository postingsRepository;

    record newJobAppRequest(
        Integer worker_id,
        Integer posting_id,
        String coverLetter
    ){}

    @PostMapping("add")
    public Object addJobApplication(@RequestBody newJobAppRequest request) {
/*         Worker worker = workerRepository.findWorkerById(request.worker_id()); */
        Worker test = workerRepository.findById(request.worker_id()).get();
        Postings test3 = postingsRepository.findById(request.posting_id()).get();
/*         Company company = companyRepository.findById(request.company_id());
        Postings posting = postingsRepository.findPostingsById(request.posting_id()); */
        JobApplications jobApplications = new JobApplications();
        jobApplications.worker(test);
        jobApplications.posting(test3);
        jobApplications.coverLetter(request.coverLetter());
        repository.save(jobApplications);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("all")
    public List<JobApplications> allJobApplications() {
        return repository.findAll();
    }

    @GetMapping("verify/{worker_id}/{posting_id}")
    public Object verifyJobApplication(@PathVariable Number worker_id, @PathVariable Number posting_id) {
        if(repository.existsByWorker_idAndPosting_id(worker_id, posting_id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
