package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.permission.PermissionRequest;
import com.lectura.apgmb.dtos.permission.PermissionResponse;
import com.lectura.apgmb.entities.secutiry.GrantedPermission;
import com.lectura.apgmb.entities.secutiry.Operation;
import com.lectura.apgmb.entities.secutiry.Role;
import com.lectura.apgmb.exceptions.ObjectNotFoundException;
import com.lectura.apgmb.repositories.auth.OperationRepository;
import com.lectura.apgmb.repositories.auth.PermissionRepository;
import com.lectura.apgmb.repositories.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {

        GrantedPermission permission = new GrantedPermission();

        Role role = roleRepository.findByName(permissionRequest.getRoleName())
                .orElseThrow(() -> new ObjectNotFoundException("Role not found: " + permissionRequest.getRoleName()));

        permission.setRole(role);

        Operation operation = operationRepository.findByName(permissionRequest.getOperationName())
                .orElseThrow(() -> new ObjectNotFoundException("Operation not found: " + permissionRequest.getOperationName()));

        permission.setOperation(operation);

        permissionRepository.save(permission);

        return mapDTO(permission);
    }

    private PermissionResponse mapDTO(GrantedPermission permission) {
        PermissionResponse response = new PermissionResponse();
        response.setIdPermission(permission.getId());
        response.setMessage("Permiso creado exitosamente");

        return response;
    }
}
