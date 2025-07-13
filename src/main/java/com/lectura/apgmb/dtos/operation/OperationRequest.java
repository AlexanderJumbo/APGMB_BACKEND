package com.lectura.apgmb.dtos.operation;

import java.io.Serializable;

public class OperationRequest implements Serializable {
    private String pathOperation;
    private String nameOperation;
    private String methodOperation;
    private String moduleName;
    private boolean isPublic;

    public String getPathOperation() {
        return pathOperation;
    }

    public void setPathOperation(String pathOperation) {
        this.pathOperation = pathOperation;
    }

    public String getNameOperation() {
        return nameOperation;
    }

    public void setNameOperation(String nameOperation) {
        this.nameOperation = nameOperation;
    }

    public String getMethodOperation() {
        return methodOperation;
    }

    public void setMethodOperation(String methodOperation) {
        this.methodOperation = methodOperation;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
