package com.yahir.Workaholic.UploadResume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yahir.Workaholic.UploadResume.UploadMessage.ResponseMessage;
import com.yahir.Workaholic.UploadResume.UploadService.FileStorageService;

@Controller
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/upload")
public class UploadResumeController {
    
    @Autowired
    FileStorageService storageService;

    private final UploadResumeRepository repository;

    public UploadResumeController(UploadResumeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public Object uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {
        String message = "";
        try {
            storageService.save(file, email);

            message = "Uploaded file successfully" + file.getOriginalFilename();
            UploadResume uResume = new UploadResume();
            uResume.setEmail(email);
            uResume.setFilename(file.getOriginalFilename());
            repository.save(uResume);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file" + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        
        }
    }

    @GetMapping("/test")
    public Object test() {
        return "Hola";
    }
}