package com.lectura.apgmb.dtos;

import java.io.Serializable;

public class RoleRequest implements Serializable {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
