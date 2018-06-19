package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.lockdeviceDao;
import org.kzcw.dao.organizationDao;
import org.kzcw.model.lockdevice;
import org.kzcw.model.organization;
import org.kzcw.service.lightboxService;
import org.kzcw.service.organizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("organizationService")
@Component
public class organizationServiceImpl extends BaseServiceImpl<organization> implements organizationService{
	
	@Autowired 
	private lightboxService operLogService;
	
	private organizationDao dao;
	
	
	@Autowired
	@Qualifier("organizationDao")
	public void setorganizationDao(organizationDao dao) {
		this.dao = dao;
	}


	public List<organization> list() {
		// TODO Auto-generated method stub
		return dao.getList("t_organization");
	}
}
