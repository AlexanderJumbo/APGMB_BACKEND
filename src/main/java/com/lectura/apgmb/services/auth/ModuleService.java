package com.lectura.apgmb.services.auth;

import com.lectura.apgmb.dtos.module.ModuleRequest;
import com.lectura.apgmb.dtos.module.ModuleResponse;

public interface ModuleService {

    ModuleResponse createModule(ModuleRequest moduleRequest);

}
