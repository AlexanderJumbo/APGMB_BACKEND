package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.meter.MeterResponse;
import com.lectura.apgmb.dtos.meter.UpdateMeterRequest;
import com.lectura.apgmb.dtos.meter.UpdateMeterResponse;
import com.lectura.apgmb.entities.WaterMeter;
import com.lectura.apgmb.entities.secutiry.User;

import java.util.List;
import java.util.Optional;

public interface WaterMeterService {

    WaterMeter createMeter(WaterMeter meter);
    List<MeterResponse> getAllMeters();
    Optional<WaterMeter> findById(Long idMeter);
    UpdateMeterResponse updateMeter(UpdateMeterRequest updateMeterRequest);
    Boolean deleteLogicMeter(Long idMeter);
    Optional<WaterMeter> findByMeterNumber(String meterNumber);
}
