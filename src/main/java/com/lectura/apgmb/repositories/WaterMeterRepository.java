package com.lectura.apgmb.repositories;

import com.lectura.apgmb.entities.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WaterMeterRepository extends JpaRepository<WaterMeter, Long> {

    Optional<WaterMeter> findByMeterNumber(String meterNumber);

}
