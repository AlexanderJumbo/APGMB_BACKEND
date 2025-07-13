package com.lectura.apgmb.controllers;

import com.lectura.apgmb.dtos.meter.MeterResponse;
import com.lectura.apgmb.dtos.meter.UpdateMeterRequest;
import com.lectura.apgmb.dtos.meter.UpdateMeterResponse;
import com.lectura.apgmb.entities.WaterMeter;
import com.lectura.apgmb.services.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meter")
public class WaterMeterController {

    @Autowired
    private WaterMeterService meterService;

    @PostMapping
    public ResponseEntity<WaterMeter> createMeter(@RequestBody WaterMeter meter){
        return ResponseEntity.status(HttpStatus.CREATED).body(meterService.createMeter(meter));
    }

    @GetMapping
    public ResponseEntity<List<MeterResponse>> getAllMeters(){
        return ResponseEntity.status(HttpStatus.OK).body(meterService.getAllMeters());
        //return ResponseEntity.status(HttpStatus.OK).body(meterService.getAllMeters());
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateMeterResponse> updateMeter(@RequestBody UpdateMeterRequest updateMeterRequest){
        return ResponseEntity.status(HttpStatus.OK).body(meterService.updateMeter(updateMeterRequest));
    }


}
