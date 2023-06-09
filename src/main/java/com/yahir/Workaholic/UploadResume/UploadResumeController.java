package com.yahir.Workaholic.UploadResume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yahir.Workaholic.Resume.Resume;
import com.yahir.Workaholic.Resume.ResumeRepository;
import com.yahir.Workaholic.UploadResume.UploadMessage.ResponseMessage;
import com.yahir.Workaholic.UploadResume.UploadService.FileStorageService;

@Controller
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/upload")
public class UploadResumeController {
    
    @Autowired
    FileStorageService storageService;

    private final ResumeRepository resumeRepository;

    public UploadResumeController(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @PostMapping("")
    public Object uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {
        String message = "";
        try {
            storageService.save(file, email);

            message = "Uploaded file successfully" + file.getOriginalFilename();
            String resumePath = "uploads/" + email;
            Resume resume = new Resume();
            resume.setPath(resumePath);
            resumeRepository.save(resume);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file" + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        
        }
    }

    @GetMapping("/{email}")
    public Object getFile(@PathVariable String email) {
        Object file = storageService.load(email);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=");
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
        headers.set(HttpHeaders.CONTENT_ENCODING, "UTF-8");
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    public void deleteFile(String email) {
        storageService.delete(email);
    }


    @GetMapping("/test")
    public Object test() {
        return "Hola";
    }
}
