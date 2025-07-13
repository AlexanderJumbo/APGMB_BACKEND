package com.lectura.apgmb.dtos;

import java.io.Serializable;

public class SectorsListResponse implements Serializable {

    private Long sectorId;
    private String nameSector;

    public SectorsListResponse(Long sectorId, String nameSector) {
        this.sectorId = sectorId;
        this.nameSector = nameSector;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }
}
