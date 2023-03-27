package com.yahir.Workaholic.Workers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yahir.Workaholic.Workers.Storage.StorageFileNotFoundException;
import com.yahir.Workaholic.Workers.Storage.StorageService;

/* import com.yahir.Workaholic.Workers.Storage.StorageService; */

@Controller
@RestController
@RequestMapping("/api/v1/worker")
@CrossOrigin
public class WorkerController {
    
    private final WorkerRepository repository;
    private final StorageService storageService;

    @Autowired
    public WorkerController(WorkerRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    record NewWorkerDataRequest (
        String FName,
        String LName,
        String Email,
        String Password,
        String Country,
        String[] Tags
    ) {}

    record NewWorkerResumeRequest (
        MultipartFile File,
        String Email
    ){}

    @GetMapping("")
    List<Worker> Testing() {
        return repository.findAll();
    }

    @PostMapping("data")
    public Object RegisterWorker(@RequestBody NewWorkerDataRequest request) {
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("resume")
    public Object RegisterResume(@RequestBody NewWorkerResumeRequest request) {
        try {
            byte[] bytes = request.File().getBytes();
            Path path = Paths.get("./ResumeStorage", request.File().getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        } 
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* @ExceptionHandler(StorageFileNotFoundException.class) */
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
    
}
