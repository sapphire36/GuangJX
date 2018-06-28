package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.BreakhistoryDao;
import org.kzcw.model.Breakhistory;
import org.kzcw.service.BreakhistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("breakhistoryService")
@Component
public class BreakhistoryServiceImp extends BaseServiceImpl<Breakhistory> implements BreakhistoryService{

	@Autowired 
	private BreakhistoryDao dao;
	
	
	@Autowired
	@Qualifier("breakhistoryDao")
	public void setbreakhistoryDao(BreakhistoryDao dao) {
		this.dao = dao;
	}
}
