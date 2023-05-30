package com.yahir.Workaholic.JobApplications;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, Integer>{
    
    Boolean existsByUser_id(Number id);

    Boolean existsByUserIdAndPostingId(Number UserId, Number PostingId);

    Set<JobApplications> findAllByPostingId(Number id);

    Set<JobApplications> findAllByUserId(Number userId);

    @Transactional
    void deleteAllByUserId(Number UserId);

    @Transactional
    void deleteAllByPostingId(Number id);
}
