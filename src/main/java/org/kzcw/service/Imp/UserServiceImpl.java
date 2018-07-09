package org.kzcw.service.Imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	@Override
	public int doLogin(String uname, String passwd, long type) {
		//执行登录
		//返回值 0:用户名或密码错误,1:登录成功 2:用户被禁用
		List<User> total;
		int ret=0; //返回值
		Session session = null;
		try {
			session = dao.OpenSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(
						Restrictions.and( //AND
							Restrictions.eq("USERNAME",uname),//名字等于uname
							Restrictions.eq("PASSWD",passwd),
							Restrictions.eq("ROLEID",type)
						)
					);
			total=criteria.list();
			if(total!=null) {
				//如果找到该用户
				if(total.size()>0) {
					if(total.get(0).getSTATUS()==0) {
						//被禁用
						ret=2;
					}else {
						ret=1;
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}
	
}
