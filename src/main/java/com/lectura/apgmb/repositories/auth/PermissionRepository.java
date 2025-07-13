package com.lectura.apgmb.repositories.auth;

import com.lectura.apgmb.entities.secutiry.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<GrantedPermission, Long> {
}
