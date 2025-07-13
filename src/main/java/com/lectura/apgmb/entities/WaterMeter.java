package com.lectura.apgmb.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class WaterMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meterId;
    private String meterNumber;
    private String meterMark;
    private Date CreateAt;

    private Long userCreated;
    private Date dateModified;
    private Long userModified;
    private Date dateElimination;
    private Long userElimination;
    private boolean active = true;

    @OneToOne(mappedBy = "waterMeter")
    private Account account;

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getMeterMark() {
        return meterMark;
    }

    public void setMeterMark(String meterMark) {
        this.meterMark = meterMark;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public Long getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Long getUserModified() {
        return userModified;
    }

    public void setUserModified(Long userModified) {
        this.userModified = userModified;
    }

    public Date getDateElimination() {
        return dateElimination;
    }

    public void setDateElimination(Date dateElimination) {
        this.dateElimination = dateElimination;
    }

    public Long getUserElimination() {
        return userElimination;
    }

    public void setUserElimination(Long userElimination) {
        this.userElimination = userElimination;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
