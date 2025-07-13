package com.lectura.apgmb.dtos.module;

import java.io.Serializable;

public class ModuleRequest implements Serializable {

    private String basePathModule;
    private String nameModule;

    public String getBasePathModule() {
        return basePathModule;
    }

    public void setBasePathModule(String basePathModule) {
        this.basePathModule = basePathModule;
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }
}
