package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.organizationDao;
import org.kzcw.dao.roleDao;
import org.kzcw.model.organization;
import org.kzcw.model.role;
import org.kzcw.service.lightboxService;
import org.kzcw.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("roleService")
@Component
public class roleServiceImpl extends BaseServiceImpl<role> implements roleService{
	
	@Autowired 
	private lightboxService operLogService;
	
	private roleDao dao;
	
	
	@Autowired
	@Qualifier("roleDao")
	public void setroleDao(roleDao dao) {
		this.dao = dao;
	}


	public List<role> list() {
		// TODO Auto-generated method stub
		return dao.getList("t_role");
	}
}
