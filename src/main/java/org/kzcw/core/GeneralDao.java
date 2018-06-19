package org.kzcw.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class GeneralDao<T extends Serializable> extends BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;
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

	@SuppressWarnings({ "unchecked" })
	public GeneralDao() {
		// 获取当前泛型类
		setEntityClass((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	/**
	 * 保存实例
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
	 * @param t
	 * @throws HibernateException void
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
	 * @param t
	 * @throws HibernateException void
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


	/**
	 * 列表
	 * @param dgm
	 * @param query
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(Query query) {
		List<T> list = null;
 
		return list;
	}

	@SuppressWarnings("unchecked")
	public T findUniqueBy(String propertyName, Object value, Query query) {
		T t = null;
		Session session = null;
		try {
 
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return t;
	}
}
