package com.yahir.Workaholic.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
    Boolean findByName(String name);
}
