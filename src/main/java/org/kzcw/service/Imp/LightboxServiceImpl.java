package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.LightboxDao;
import org.kzcw.model.Lightbox;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lightboxService")
@Component
public class LightboxServiceImpl extends BaseServiceImpl<Lightbox> implements LightboxService{


	private LightboxDao dao;
	
	
	@Autowired
	@Qualifier("lightboxDao")
	public void setlightboxDao(LightboxDao dao) {
		this.dao = dao;
	}
}
