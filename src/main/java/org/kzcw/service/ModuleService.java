package org.kzcw.service;
import org.kzcw.core.BaseService;
import org.kzcw.model.Module;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("moduleService")

public interface ModuleService extends BaseService<Module>{

}
