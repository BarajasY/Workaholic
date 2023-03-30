package com.yahir.Workaholic.UploadResume;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadResumeRepository extends JpaRepository<UploadResume, Integer> {
    
    UploadResume getResumeByEmail(String email);
}
