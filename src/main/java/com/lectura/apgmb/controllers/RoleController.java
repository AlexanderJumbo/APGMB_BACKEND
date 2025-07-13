package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.RoleRequest;
import com.lectura.apgmb.entities.secutiry.Role;
import com.lectura.apgmb.services.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest roleRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRequest));
    }

}
