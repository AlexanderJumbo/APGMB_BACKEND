package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.module.ModuleRequest;
import com.lectura.apgmb.dtos.module.ModuleResponse;
import com.lectura.apgmb.dtos.operation.OperationRequest;
import com.lectura.apgmb.dtos.operation.OperationResponse;
import com.lectura.apgmb.services.auth.ModuleService;
import com.lectura.apgmb.services.auth.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping
    public ResponseEntity<ModuleResponse> createNewModule(@RequestBody ModuleRequest moduleRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.createModule(moduleRequest));
    }
}
