package com.yahir.Workaholic.JobTypes;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeRepository extends JpaRepository<JobType, Integer> {
    
    Boolean existsByType(String type);

    Set<JobType> findAllByType(String[] type);

    JobType findByType(String type);
}
