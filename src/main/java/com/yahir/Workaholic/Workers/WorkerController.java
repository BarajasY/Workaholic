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
    public WorkerController(WorkerRepository repository) {
        this.repository = repository;
    }

    record NewWorkerDataRequest(
        String FName,
        String LName,
        String Email,
        String Password,
        String Country,
        String Tags
    ){}

    record NewWorkerLogin(
        String Email,
        String Password
    ){}

    @GetMapping("")
    List<Worker> Testing() {
        return repository.findAll();
    }

    @PostMapping("data")
    public Object RegisterWorker(@RequestBody NewWorkerDataRequest request) {
        if(repository.existsByEmail(request.Email())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Worker Worker = new Worker(null, null, null, null, null, null, null);
        Worker.setFname(request.FName());
        Worker.setLname(request.LName());
        Worker.setEmail(request.Email());
        Worker.setPassword(request.Password());
        Worker.setCountry(request.Country());
        Worker.setTags(request.Tags());
        repository.save(Worker);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("login")
    public Object LoginWorker(@RequestBody NewWorkerLogin request) {
        Boolean Verify = repository.existsByEmail(request.Email());
        if(Verify) {
            Worker DatabaseWorker = repository.findWorkerByEmail(request.Email());
            if(DatabaseWorker.getPassword().equals(request.Password())) {
                return DatabaseWorker;
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
