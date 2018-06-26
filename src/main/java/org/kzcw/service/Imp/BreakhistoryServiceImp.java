package org.kzcw.service.Imp;

import java.util.List;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.BreakhistoryDao;
import org.kzcw.dao.LockdeviceDao;
import org.kzcw.model.Breakhistory;
import org.kzcw.service.BreakhistoryService;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("breakhistoryService")
@Component
public class BreakhistoryServiceImp extends BaseServiceImpl<Breakhistory> implements BreakhistoryService{

	@Autowired 
	private LightboxService operLogService;
	private BreakhistoryDao dao;
	
	
	@Autowired
	@Qualifier("breakhistoryDao")
	public void setbreakhistoryDao(BreakhistoryDao dao) {
		this.dao = dao;
	}
}
