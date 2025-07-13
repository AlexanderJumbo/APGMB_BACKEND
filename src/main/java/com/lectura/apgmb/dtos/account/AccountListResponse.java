package com.lectura.apgmb.dtos.account;

import java.io.Serializable;
import java.util.Date;

public class AccountListResponse implements Serializable {
    private Long accountId;
    private Long userId;
    private Long sectorId;
    private String name;
    private String lastname;
    private String address;
    private String dni;
    private String phone;
    private String email;
    private Long meterId;
    private String meterNumber;
    private String meterMark;
    private String predialCode;
    private Date dateRegister;
    private boolean isActive;
    private String  nameSector;
    public AccountListResponse() {
    }

    public AccountListResponse(Long accountId, Long userId, Long sectorId, String name, String lastname, String address, String dni, String phone, String email, Long meterId, String meterNumber, String meterMark, String predialCode, Date dateRegister, boolean isActive, String nameSector) {
        this.accountId = accountId;
        this.userId = userId;
        this.sectorId = sectorId;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
        this.meterId = meterId;
        this.meterNumber = meterNumber;
        this.meterMark = meterMark;
        this.predialCode = predialCode;
        this.dateRegister = dateRegister;
        this.isActive = isActive;
        this.nameSector = nameSector;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPredialCode() {
        return predialCode;
    }

    public void setPredialCode(String predialCode) {
        this.predialCode = predialCode;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }
}
