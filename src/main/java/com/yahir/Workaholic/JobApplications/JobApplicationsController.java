package com.yahir.Workaholic.JobApplications;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/jobapplication")
public class JobApplicationsController {
    
    private JobApplicationsRepository repository;

    
}
