package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.ModuleDao;
import org.kzcw.model.Module;
import org.kzcw.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("moduleService")
@Component
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService{
	
	private ModuleDao dao;
	
	
	@Autowired
	@Qualifier("moduleDao")
	public void setlockdeviceDao(ModuleDao d) {
		this.dao = d;
	}
}
