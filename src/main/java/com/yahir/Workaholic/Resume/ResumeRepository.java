package com.yahir.Workaholic.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    
    Resume findByPath(String path);
}
