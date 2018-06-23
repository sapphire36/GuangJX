package org.kzcw.service.Imp;

import java.util.List;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.LockdeviceDao;
import org.kzcw.model.Lockdevice;
import org.kzcw.service.LightboxService;
import org.kzcw.service.LockdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lockdeviceService")
@Component
public class LockdeviceServiceImpl extends BaseServiceImpl<Lockdevice> implements LockdeviceService{
	
	@Autowired 
	private LightboxService operLogService;
	
	private LockdeviceDao dao;
	
	
	@Autowired
	@Qualifier("lockdeviceDao")
	public void setlockdeviceDao(LockdeviceDao dao) {
		this.dao = dao;
	}
}
