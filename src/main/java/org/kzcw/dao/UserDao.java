package org.kzcw.dao;
import org.kzcw.core.BaseDao;
import org.kzcw.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("userDao")
@Component
public class UserDao extends BaseDao<User>{
	
}

