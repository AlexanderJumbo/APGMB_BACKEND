package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.SectorsListResponse;
import com.lectura.apgmb.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorServiceImpl implements SectorService{

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public List<SectorsListResponse> getAllSectors() {

        return sectorRepository.findAll().stream().map(s -> new SectorsListResponse(
            s.getSectorId(), s.getNameSector()
        )).collect(Collectors.toList());
    }
}
