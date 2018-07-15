package org.kzcw.service;
import java.util.List;

import org.kzcw.core.BaseService;
import org.kzcw.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("userService")
public interface UserService extends BaseService<User>{

	public List<User> keeList();
	
	public User search(int id);
	
    public int doLogin(String uname,String passwd,long type);
	
    public User getUserByNameAndPasswd(String uname,String passwd,long type);
}
