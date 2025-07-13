package com.lectura.apgmb.dtos.account;

import java.io.Serializable;

public class AccountRequest implements Serializable {
    private String name;
    private String lastname;
    private String email;
    private String dni;
    private String address;
    private String phone;
    private String role;
    private String meterNumber;
    private String meterMark;
    private String predialCode;
    private String nameSector;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }
}
