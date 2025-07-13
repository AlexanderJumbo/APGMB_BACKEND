package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.RoleRequest;
import com.lectura.apgmb.entities.secutiry.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
    Optional<Role> findByName(String roleName);

    Role createRole(RoleRequest roleRequest);
}
