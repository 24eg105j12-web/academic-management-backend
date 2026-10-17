package com.anurag.cse.repository;

import com.anurag.cse.entity.Role;
import com.anurag.cse.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
