package org.kzcw.dao;

import org.kzcw.core.BaseDao;
import org.kzcw.model.Lightbox;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("lightboxDao")
@Component
public class LightboxDao extends BaseDao<Lightbox> {
	
}