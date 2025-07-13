package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.module.ModuleRequest;
import com.lectura.apgmb.dtos.module.ModuleResponse;
import com.lectura.apgmb.entities.secutiry.Module;
import com.lectura.apgmb.repositories.auth.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService{

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public ModuleResponse createModule(ModuleRequest moduleRequest) {

        Module newModule = new Module();
        newModule.setBasePath(moduleRequest.getBasePathModule());
        newModule.setName(moduleRequest.getNameModule());

        moduleRepository.save(newModule);

        return mapDTO(newModule);
    }

    private ModuleResponse mapDTO(Module newModule) {

        ModuleResponse response = new ModuleResponse();
        response.setIdModule(newModule.getId());
        response.setMessage("Módulo creado exitosamente");

        return response;
    }
}
