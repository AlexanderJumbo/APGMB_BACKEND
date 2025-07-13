package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.RoleRequest;
import com.lectura.apgmb.entities.secutiry.Role;
import com.lectura.apgmb.repositories.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName("OPERATIONAL");
    }

    @Override
    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role createRole(RoleRequest roleRequest) {

        Role newRole = new Role();
        newRole.setName(roleRequest.getName());

        return roleRepository.save(newRole);
    }
}
