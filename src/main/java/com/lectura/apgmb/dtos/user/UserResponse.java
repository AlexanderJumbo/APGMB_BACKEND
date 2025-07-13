package com.lectura.apgmb.dtos.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class UserResponse implements Serializable {
    private Long idUser;
    private String name;
    private String lastname;
    private String userName;
    private String address;
    private String email;
    private String dni;
    private String numberPhone;
    private String photo;
    private Date dateRegister;
    private String role;

    public UserResponse() {
    }

    public UserResponse(Long idUser, String name, String lastname, String userName, String address, String email, String dni, String numberPhone, String photo, Date dateRegister, String role) {
        this.idUser = idUser;
        this.name = name;
        this.lastname = lastname;
        this.userName = userName;
        this.address = address;
        this.email = email;
        this.dni = dni;
        this.numberPhone = numberPhone;
        this.photo = photo;
        this.dateRegister = dateRegister;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
