package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.OperatehistoryDao;
import org.kzcw.model.Operatehistory;
import org.kzcw.service.LightboxService;
import org.kzcw.service.OperatehistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("operatehistoryService")
@Component
public class OperatehistoryServiceImpl extends BaseServiceImpl<Operatehistory> implements OperatehistoryService{
	
	@Autowired 
	private LightboxService operLogService;
	
	private OperatehistoryDao dao;
	
	
	@Autowired
	@Qualifier("operatehistoryDao")
	public void setoperatehistoryDao(OperatehistoryDao dao) {
		this.dao = dao;
	}

}
