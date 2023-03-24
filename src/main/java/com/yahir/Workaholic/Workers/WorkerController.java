package com.yahir.Workaholic.Workers;

import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/worker")
public class WorkerController {
    
    private final WorkerRepository repository;

    public WorkerController(WorkerRepository repository) {
        this.repository = repository;
    }

    record NewWorkerSignupRequest (
        String FName,
        String LName,
        String Email,
        String Password,
        String Country,
        String[] Tags,
        File CV
    ) {}

    @GetMapping("")
    List<Worker> Testing() {
        return repository.findAll();
    }
    
}
