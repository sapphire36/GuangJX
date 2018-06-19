package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.operatehistoryDao;
import org.kzcw.model.operatehistory;
import org.kzcw.service.lightboxService;
import org.kzcw.service.operatehistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("operatehistoryService")
@Component
public class operatehistoryServiceImpl extends BaseServiceImpl<operatehistory> implements operatehistoryService{
	
	@Autowired 
	private lightboxService operLogService;
	
	private operatehistoryDao dao;
	
	
	@Autowired
	@Qualifier("operatehistoryDao")
	public void setoperatehistoryDao(operatehistoryDao dao) {
		this.dao = dao;
	}


	public List<operatehistory> list() {
		// TODO Auto-generated method stub
		return dao.getList("t_operatehistory");
	}
}
