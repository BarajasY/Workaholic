package com.yahir.Workaholic.JobApplications;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, Integer>{
    
    Boolean existsByUser_id(Number id);

    Boolean existsByUserIdAndPostingId(Number UserId, Number PostingId);

    Set<JobApplications> findAllByPostingId(Number id);
}
