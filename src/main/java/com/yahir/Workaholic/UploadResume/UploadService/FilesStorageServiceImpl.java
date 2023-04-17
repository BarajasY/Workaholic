package com.yahir.Workaholic.UploadResume.UploadService;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FileStorageService{
    
    private final Path root = Paths.get("uploads");

    @Override 
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, String email) {
        String filename = email + ".pdf";
        try {
            Files.copy(file.getInputStream(), this.root.resolve(filename));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Object load(String email) {
        try {
            String filename = email + ".pdf";
            URI filepath = root.toAbsolutePath().toUri().resolve(filename);
            Resource resource = new UrlResource(filepath);
            return resource;
        } catch (Exception e) {
            return e;
        }
    }
}
