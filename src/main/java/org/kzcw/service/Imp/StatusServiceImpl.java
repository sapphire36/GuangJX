package org.kzcw.service.Imp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.kzcw.core.BaseServiceImpl;
import org.kzcw.model.Status;
import org.kzcw.model.User;
import org.kzcw.service.StatusService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service("StatusService")
@Component
public class StatusServiceImpl extends BaseServiceImpl<Status> implements StatusService{

	@Override
	public boolean deleteAllItem() {
		//清空数据表
		String sql="delete from t_status";
		boolean result=false;
		try {
			result=dao.ExecSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
			result=false;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Status getRecentRecord(String ieme) {
		//获取最近的一条记录
		List<Status> total;
		Session session = null;
		try {
			session = dao.OpenSession();
			Criteria criteria = session.createCriteria(Status.class);
			criteria.add(Restrictions.eq("IEME",ieme));//找到IEME为ieme的记录
			criteria.addOrder(Order.desc("ADDTIME"));
			total=criteria.list();
			if(total!=null) {
				//如果找到该用户
				if(total.size()>0) {
					//返回第一条记录
				    return total.get(0);
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
 
}
