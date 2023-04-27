package com.yahir.Workaholic.JobTypes;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobTypeController {
    
    private JobTypeRepository repository;

    public JobTypeController(JobTypeRepository repository) {
        this.repository = repository;
        createJobTypes("Full-Time");
        createJobTypes("Part-Time");
        createJobTypes("Freelance");
    }

    public void createJobTypes(String type) {
        JobType jobtype = new JobType();
        jobtype.type(type);
        repository.save(jobtype);
    }


}
