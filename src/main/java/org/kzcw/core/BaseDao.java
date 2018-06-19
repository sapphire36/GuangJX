package org.kzcw.core;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseDao<T extends Serializable> {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@SuppressWarnings({ "unchecked" })
	public BaseDao() {
		// 获取当前泛型类
		setEntityClass((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	// 当前泛型类
	@SuppressWarnings("rawtypes")
	private Class entityClass;

	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		return entityClass;
	}

	@SuppressWarnings("rawtypes")
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	//数据库备份与还原
	//sql sql语句，返回值0代表成功，1代表失败
	public int backup(String sql) {
		int flag=-1;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.createSQLQuery(sql).executeUpdate();
			flag=0;
			session.getTransaction().commit();
		} catch (HibernateException e) {
			flag=1;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
	

	@SuppressWarnings("unchecked")
	public List<T> findEntryByExecSQL(StringBuffer querySql) {
		List<T> list = null;
		Session session = null;

		try {
			String sql = "";
			sql = querySql.toString();
			session = sessionFactory.openSession();
			list = session
					.createSQLQuery(sql)
					.setResultTransformer(
							Transformers.aliasToBean(getEntityClass())).list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public boolean ExecSQL(StringBuffer querySql) {
		Session session = null;
		try {
			String sql = "";
			sql = querySql.toString();
			session = sessionFactory.openSession();
			session.createSQLQuery(sql).executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> findMapByExecSQL(StringBuffer querySql) {
		List<Map> list = null;
		Session session = null;

		try {
			String sql = "";
			sql = querySql.toString();
			session = sessionFactory.openSession();
			list = session
					.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public int save(StringBuffer querySql) {
		int row = 0;
		Session session = null;

		try {
			String sql = "";
			sql = querySql.toString();
			session = sessionFactory.openSession();
			session.beginTransaction();
			row = session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return row;
	}

	/**
	 * 保存实例
	 * 
	 * @param t
	 * @throws HibernateException
	 */
	public void save(T t) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 修改实例
	 * 
	 * @param t
	 * @throws HibernateException
	 *             void
	 */
	public void update(T t) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
	}

	/**
	 * 删除实例
	 * 
	 * @param t
	 * @throws HibernateException
	 *             void
	 */
	public void delete(T t) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
	}

  
	protected org.hibernate.Query createQuery(StringBuffer queryString,
			Object... values) {
		org.hibernate.Query query = getSession().createQuery(
				queryString.toString());
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}


	@SuppressWarnings("unchecked")
	public List<Object> queryByWhere(StringBuffer queryString, Object... values) {
		List<Object> total = null;
		String fromHql = queryString.toString();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			org.hibernate.Query query = this.createQuery(queryString, values);
			try {
				total = (List<Object>) query.list();
			} catch (Exception e) {
				throw new RuntimeException("hql can't be auto count, hql is:"
						+ fromHql, e);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(String table) {
		List<T> total = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "from "+table; 
			Query que = session.createQuery(sql); 
			total = que.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}
}
