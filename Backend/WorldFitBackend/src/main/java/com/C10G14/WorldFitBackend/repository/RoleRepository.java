package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole roleName);
}
