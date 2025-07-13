package com.lectura.apgmb.repositories.auth;

import com.lectura.apgmb.entities.secutiry.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
}
