package org.kzcw.service.Imp;

import java.util.List;

import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.roleDao;
import org.kzcw.dao.userDao;
import org.kzcw.model.role;
import org.kzcw.model.user;
import org.kzcw.service.lightboxService;
import org.kzcw.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("userService")
@Component
public class userServiceImpl extends BaseServiceImpl<user> implements userService{
	
	@Autowired 
	private lightboxService userService;
	
	
	private userDao dao;
	
	
	@Autowired
	@Qualifier("userDao")
	public void setuserDao(userDao dao) {
		this.dao = dao;
	}


	public List<user> list() {
		// TODO Auto-generated method stub
		return dao.getList("t_user");
	}
	

	public List<user> keeList() {
		// TODO Auto-generated method stub
		return null;
	}

	public user search(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
