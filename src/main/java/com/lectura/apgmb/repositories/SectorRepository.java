package com.lectura.apgmb.repositories;

import com.lectura.apgmb.entities.Sector;
import com.lectura.apgmb.entities.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    Optional<Sector> findByNameSector(String nameSector);
}
