package com.lectura.apgmb.dtos.account;

import java.io.Serializable;

public class AccountUserResponse implements Serializable {

    private Long idUsuario;
    private String fullname;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
