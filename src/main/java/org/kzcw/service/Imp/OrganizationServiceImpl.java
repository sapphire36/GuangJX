package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.OrganizationDao;
import org.kzcw.model.Organization;
import org.kzcw.service.LightboxService;
import org.kzcw.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("organizationService")
@Component
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService{
	
	@Autowired 
	private LightboxService operLogService;
	
	private OrganizationDao dao;
	
	
	@Autowired
	@Qualifier("organizationDao")
	public void setorganizationDao(OrganizationDao dao) {
		this.dao = dao;
	}


	public List<Organization> list() {
		// TODO Auto-generated method stub
		return dao.getList("Organization");
	}
}
