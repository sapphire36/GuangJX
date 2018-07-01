package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.RoleDao;
import org.kzcw.model.Role;
import org.kzcw.service.LightboxService;
import org.kzcw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("roleService")
@Component
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
	
	@Autowired 
	private LightboxService operLogService;
	
	private RoleDao dao;
	
	
	@Autowired
	@Qualifier("roleDao")
	public void setroleDao(RoleDao dao) {
		this.dao = dao;
	}
}
