package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.role;
import org.kzcw.service.lightboxService;
import org.kzcw.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("roleService")
@Component
public class roleServiceImpl extends BaseServiceImpl<role> implements roleService{
	
	@Autowired 
	private lightboxService operLogService;
}
