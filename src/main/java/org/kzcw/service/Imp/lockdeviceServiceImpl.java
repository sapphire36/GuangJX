package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.lockdeviceDao;
import org.kzcw.model.lockdevice;
import org.kzcw.service.lightboxService;
import org.kzcw.service.lockdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lockdeviceService")
@Component
public class lockdeviceServiceImpl extends BaseServiceImpl<lockdevice> implements lockdeviceService{
	
	@Autowired 
	private lightboxService operLogService;
	
	private lockdeviceDao dao;
	
	
	@Autowired
	@Qualifier("lockdeviceDao")
	public void setlockdeviceDao(lockdeviceDao dao) {
		this.dao = dao;
	}


	public List<lockdevice> list() {
		// TODO Auto-generated method stub
		return dao.getList("t_lockdevice");
	}
}
