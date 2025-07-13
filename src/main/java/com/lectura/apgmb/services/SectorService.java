package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.SectorsListResponse;
import com.lectura.apgmb.dtos.account.AccountListResponse;

import java.util.List;

public interface SectorService {
    List<SectorsListResponse> getAllSectors();
}
