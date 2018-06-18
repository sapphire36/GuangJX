package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.lockdevice;
import org.kzcw.service.lightboxService;
import org.kzcw.service.lockdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lockdeviceService")
@Component
public class lockdeviceServiceImpl extends BaseServiceImpl<lockdevice> implements lockdeviceService{
	
	@Autowired 
	private lightboxService operLogService;
}
