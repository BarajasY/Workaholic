package com.yahir.Workaholic.Workers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/worker")
@CrossOrigin
public class WorkerController {
    
    private final WorkerRepository repository;

    @Autowired
    public WorkerController(WorkerRepository repository/* , StorageService storageService */) {
        this.repository = repository;
    }

    record NewWorkerDataRequest (
        String FName,
        String LName,
        String Email,
        String Password,
        String Country,
        String[] Tags
    ) {}

    @GetMapping("")
    List<Worker> Testing() {
        return repository.findAll();
    }

    @PostMapping("data")
    public Object RegisterWorker(@RequestBody NewWorkerDataRequest request) {
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
