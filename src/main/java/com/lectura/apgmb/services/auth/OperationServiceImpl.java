package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.operation.OperationRequest;
import com.lectura.apgmb.dtos.operation.OperationResponse;
import com.lectura.apgmb.entities.secutiry.Module;
import com.lectura.apgmb.entities.secutiry.Operation;
import com.lectura.apgmb.repositories.auth.ModuleRepository;
import com.lectura.apgmb.repositories.auth.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public OperationResponse createNewOperation(OperationRequest operationRequest) {

        Module existModule = moduleRepository.findByName(operationRequest.getModuleName()).isPresent()
                ? moduleRepository.findByName(operationRequest.getModuleName()).get()
                : null;
        if(existModule == null) return getResponse(new Operation());
        Operation newOperation = new Operation();
        newOperation.setName(operationRequest.getNameOperation());
        newOperation.setPath(operationRequest.getPathOperation());
        newOperation.setHttpMethod(operationRequest.getMethodOperation());
        newOperation.setPermitAll(operationRequest.isPublic());
        newOperation.setModule(existModule);
        operationRepository.save(newOperation);

        return getResponse(newOperation);
    }

    private static OperationResponse getResponse(Operation newOperation) {
        OperationResponse result = new OperationResponse();
        if(newOperation.getId() == null){
            result.setNameOperation(newOperation.getName());
            result.setMessage("La transacción no pudo completarse debido a que el módulo no existe en nuestros registros");
        }else{
            result.setNameOperation(newOperation.getName());
            result.setMessage("Operación creada con éxito");
        }
        return result;
    }
}
