package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.lightbox;
import org.kzcw.service.lightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lightboxService")
@Component
public class lightboxServiceImpl extends BaseServiceImpl<lightbox> implements lightboxService{

}
