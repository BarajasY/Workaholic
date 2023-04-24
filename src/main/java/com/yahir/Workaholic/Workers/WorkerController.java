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
        createAdmin();
    }

    record NewWorkerDataRequest(
        String FName,
        String LName,
        String Email,
        String Password,
        String Country,
        String Role,
        String Tags
    ){}

    record NewWorkerLogin(
        String Email,
        String Password
    ){}

    @GetMapping("/all")
    public List<Worker> Testing() {
        return repository.findAll();
    }

    @PostMapping("data")
    public Object RegisterWorker(@RequestBody NewWorkerDataRequest request) {
        if(repository.existsByEmail(request.Email())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Worker Worker = new Worker();
        Worker.setFname(request.FName());
        Worker.setLname(request.LName());
        Worker.setEmail(request.Email());
        Worker.setPassword(request.Password());
        Worker.setCountry(request.Country());
        Worker.setTags(request.Tags());
        Worker.setRole(request.Role());
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

    public void createAdmin() {
        if(repository.existsByEmail("admin@workaholic.com")) {
            return;
        }
        Worker admin = new Worker();
        admin.setCountry("Mexico");
        admin.setEmail("admin@workaholic.com");
        admin.setFname("Admin");
		admin.setLname("Admin");
		admin.setPassword("wasd");
		admin.setRole("admin");
		admin.setTags("Software");
		repository.save(admin);
	}

}
