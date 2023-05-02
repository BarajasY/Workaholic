package com.yahir.Workaholic.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    User findById(Number id);

    Boolean existsByEmail(String email);
}
