package com.lectura.apgmb.dtos.operation;

import java.io.Serializable;

public class OperationResponse implements Serializable {
    private String message;
    private String nameOperation;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameOperation() {
        return nameOperation;
    }

    public void setNameOperation(String nameOperation) {
        this.nameOperation = nameOperation;
    }
}
