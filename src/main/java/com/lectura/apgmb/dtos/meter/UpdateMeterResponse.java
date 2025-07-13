package com.lectura.apgmb.dtos.meter;

import java.io.Serializable;

public class UpdateMeterResponse implements Serializable {

    private Long IdMeter;
    private String mensaje;

    public Long getIdMeter() {
        return IdMeter;
    }

    public void setIdMeter(Long idMeter) {
        IdMeter = idMeter;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
