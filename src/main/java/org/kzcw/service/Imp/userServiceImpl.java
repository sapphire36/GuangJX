package org.kzcw.service.Imp;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.user;
import org.kzcw.service.lightboxService;
import org.kzcw.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("userService")
@Component
public class userServiceImpl extends BaseServiceImpl<user> implements userService{
	
	@Autowired 
	private lightboxService operLogService;
}
