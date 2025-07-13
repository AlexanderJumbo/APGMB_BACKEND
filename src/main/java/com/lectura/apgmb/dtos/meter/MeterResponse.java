package com.lectura.apgmb.dtos.meter;

import java.io.Serializable;
import java.util.Date;

public class MeterResponse implements Serializable {

    private Long meterId;
    private Date registerDate;
    private boolean isActive;
    private String mark;
    private String serial;

    public MeterResponse(Long meterId, Date registerDate, boolean isActive, String mark, String serial) {
        this.meterId = meterId;
        this.registerDate = registerDate;
        this.isActive = isActive;
        this.mark = mark;
        this.serial = serial;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
