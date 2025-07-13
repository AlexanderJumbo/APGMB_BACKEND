package com.lectura.apgmb.dtos.permission;

import java.io.Serializable;

public class PermissionRequest implements Serializable {

    private String roleName;
    private String operationName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
