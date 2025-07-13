package com.lectura.apgmb.dtos.module;

import java.io.Serializable;

public class ModuleResponse implements Serializable {

    private Long idModule;
    private String message;

    public Long getIdModule() {
        return idModule;
    }

    public void setIdModule(Long idModule) {
        this.idModule = idModule;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
