package com.lectura.apgmb.dtos.meter;

import java.io.Serializable;

public class UpdateMeterRequest implements Serializable {

    private Long idMeter;
    private String numberMeter;
    private String markMeter;

    public Long getIdMeter() {
        return idMeter;
    }

    public void setIdMeter(Long idMeter) {
        this.idMeter = idMeter;
    }

    public String getNumberMeter() {
        return numberMeter;
    }

    public void setNumberMeter(String numberMeter) {
        this.numberMeter = numberMeter;
    }

    public String getMarkMeter() {
        return markMeter;
    }

    public void setMarkMeter(String markMeter) {
        this.markMeter = markMeter;
    }
}
