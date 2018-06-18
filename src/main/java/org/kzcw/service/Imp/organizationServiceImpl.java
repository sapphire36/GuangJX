package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.organization;
import org.kzcw.service.lightboxService;
import org.kzcw.service.organizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("organizationService")
@Component
public class organizationServiceImpl extends BaseServiceImpl<organization> implements organizationService{
	
	@Autowired 
	private lightboxService operLogService;
}
