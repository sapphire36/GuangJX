package org.kzcw.service.Imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.dao.LockdeviceDao;
import org.kzcw.model.Lockdevice;
import org.kzcw.model.Status;
import org.kzcw.model.User;
import org.kzcw.service.LightboxService;
import org.kzcw.service.LockdeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("lockdeviceService")
@Component
public class LockdeviceServiceImpl extends BaseServiceImpl<Lockdevice> implements LockdeviceService{
	
	@Autowired 
	private LightboxService operLogService;
	
	private LockdeviceDao dao;
	
	
	@Autowired
	@Qualifier("lockdeviceDao")
	public void setlockdeviceDao(LockdeviceDao dao) {
		this.dao = dao;
	}


	@Override
	public List<Status> getStatusByIEME(String ieme) {
		// TODO Auto-generated method stub
		List<Status> total = null;
		Session session = null;
		try {
			session = dao.OpenSession();
			Criteria criteria = session.createCriteria(Status.class);
			criteria.add(Restrictions.eq("IEME",ieme));
			total=criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}
}
