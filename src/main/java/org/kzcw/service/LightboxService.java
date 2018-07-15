package org.kzcw.service;
import java.util.List;

import org.kzcw.core.BaseService;
import org.kzcw.model.Lightbox;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("lightboxService")

public interface LightboxService extends BaseService<Lightbox>{
	 
	public List<Lightbox> findByAreaName(String area);
	 
}

