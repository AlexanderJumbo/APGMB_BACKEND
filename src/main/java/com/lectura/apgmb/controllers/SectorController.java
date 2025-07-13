package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.SectorsListResponse;
import com.lectura.apgmb.dtos.account.AccountListResponse;
import com.lectura.apgmb.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sector")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping("/all")
    public ResponseEntity<List<SectorsListResponse>> getAllSectors() {
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.getAllSectors());
    }
}
