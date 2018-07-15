package org.kzcw.service.Imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.LightboxDao;
import org.kzcw.model.Lightbox;
import org.kzcw.model.User;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lightboxService")
@Component
public class LightboxServiceImpl extends BaseServiceImpl<Lightbox> implements LightboxService{


	private LightboxDao dao;
	
	
	@Autowired
	@Qualifier("lightboxDao")
	public void setlightboxDao(LightboxDao dao) {
		this.dao = dao;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Lightbox> findByAreaName(String area) {
		// TODO Auto-generated method stub
		List<Lightbox> total = null;
		Session session = null;
		try {
			session = dao.OpenSession();
			Criteria criteria = session.createCriteria(Lightbox.class);
       		//选择t_lightbox表中,AREANAME为area的值
			criteria.add(Restrictions.eq("AREANAME",area));
			total=criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}
	
	
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
