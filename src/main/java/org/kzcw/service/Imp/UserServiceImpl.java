package org.kzcw.service.Imp;

import java.util.List;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.UserDao;
import org.kzcw.model.User;
import org.kzcw.service.LightboxService;
import org.kzcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("userService")
@Component
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	@Autowired 
	private LightboxService userService;
	
	
	private UserDao dao;
	
	
	@Autowired
	@Qualifier("userDao")
	public void setuserDao(UserDao dao) {
		this.dao = dao;
	}

	public List<User> keeList() {
		// TODO Auto-generated method stub
		return null;
	}

	public User search(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
