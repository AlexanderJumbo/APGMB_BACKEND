package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.permission.PermissionRequest;
import com.lectura.apgmb.dtos.permission.PermissionResponse;
import com.lectura.apgmb.services.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(permissionRequest));
    }

}
