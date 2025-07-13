package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.operation.OperationRequest;
import com.lectura.apgmb.dtos.operation.OperationResponse;

public interface OperationService {

    OperationResponse createNewOperation(OperationRequest moduleRequest);
}
