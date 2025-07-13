package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.operation.OperationRequest;
import com.lectura.apgmb.dtos.operation.OperationResponse;
import com.lectura.apgmb.services.auth.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationResponse> createNewOperation(@RequestBody OperationRequest operationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(operationService.createNewOperation(operationRequest));
    }
}
