package org.kzcw.service;
import java.util.List;

import org.kzcw.core.BaseService;
import org.kzcw.model.user;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("userService")
public interface userService extends BaseService<user>{

	public List<user> keeList();
	
	public user search(int id);

}
