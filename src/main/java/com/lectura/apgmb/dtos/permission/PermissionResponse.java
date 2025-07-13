package com.lectura.apgmb.dtos.permission;

import java.io.Serializable;

public class PermissionResponse implements Serializable {

    private Long idPermission;
    private String message;

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
