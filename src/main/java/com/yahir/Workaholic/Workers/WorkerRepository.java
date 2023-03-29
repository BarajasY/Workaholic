package com.yahir.Workaholic.Workers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    
    Worker findWorkerByEmail(String email);

    Boolean existsByEmail(String email);
}
