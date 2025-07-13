package com.lectura.apgmb.dtos.user;

import java.io.Serializable;

public class UpdateResponse implements Serializable {
    private Long idUser;
    private String message;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
