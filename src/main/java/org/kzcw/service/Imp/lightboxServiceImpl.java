package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.lightboxDao;
import org.kzcw.model.lightbox;
import org.kzcw.service.lightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lightboxService")
@Component
public class lightboxServiceImpl extends BaseServiceImpl<lightbox> implements lightboxService{


	private lightboxDao dao;
	
	
	@Autowired
	@Qualifier("lightboxDao")
	public void setlightboxDao(lightboxDao dao) {
		this.dao = dao;
	}


	public List<lightbox> list() {
		// TODO Auto-generated method stub
		return dao.getList("t_lightbox");
	}
	
	public lightbox findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
