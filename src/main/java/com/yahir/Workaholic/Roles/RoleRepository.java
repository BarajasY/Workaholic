package com.yahir.Workaholic.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
    Boolean existsByName(String name);

    Role findByName(String name);
}
