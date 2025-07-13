package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.permission.PermissionRequest;
import com.lectura.apgmb.dtos.permission.PermissionResponse;

public interface PermissionService {

    PermissionResponse createPermission(PermissionRequest permissionRequest);

}
