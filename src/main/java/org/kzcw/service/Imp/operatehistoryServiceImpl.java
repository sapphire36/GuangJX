package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.operatehistory;
import org.kzcw.service.lightboxService;
import org.kzcw.service.operatehistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("operatehistoryService")
@Component
public class operatehistoryServiceImpl extends BaseServiceImpl<operatehistory> implements operatehistoryService{
	
	@Autowired 
	private lightboxService operLogService;
}
