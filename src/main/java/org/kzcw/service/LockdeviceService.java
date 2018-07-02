package org.kzcw.service;
import java.util.List;

import org.kzcw.core.BaseService;
import org.kzcw.model.Lockdevice;
import org.kzcw.model.Status;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component("lockdeviceService")

public interface LockdeviceService extends BaseService<Lockdevice>{
	 
	public List<Status> getStatusByIEME(String ieme);
	 
}
