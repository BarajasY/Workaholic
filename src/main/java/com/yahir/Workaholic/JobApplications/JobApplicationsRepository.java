package com.yahir.Workaholic.JobApplications;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, Integer>{
    
    Boolean existsByWorker_id(Number id);

    Boolean existsByWorker_idAndPosting_id(Number worker_id, Number posting_id);
}
