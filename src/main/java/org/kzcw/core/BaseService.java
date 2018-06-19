package org.kzcw.core;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 * 业务逻辑基础接口
 * Copyright 2013 - 2014
 * @project pdkj － BaseService
 * @author pdkj * @version 2014年3月22日下午11:41:20 - BaseService.java
 */
public interface BaseService<T> {
	/**
	 * 保存实体
	 * 默认设置以下字段的值：uuid、creatDate、lastDate、operation
	 * @param t void
	 */
	public void save(T t);

	/**
	 * 更新
	 * 默认设置以下字段的值：lastDate、operation
	 * @param t void
	 */
	public void update(T t);

	/**
	 * 删除
	 * @param t void
	 */
	public void delete(T t);

	/**
	 * 统计数量
	 * @param query
	 * @return long
	 */
	public long findCount();
	

	/**
	 * 查询
	 * @param propertyName
	 * @param value
	 * @return T
	 */
	public T findUniqueBy(String propertyName, Object value);

	/**
	 * 查询
	 * @param id
	 * @return T
	 */
	public T findById(long id);

	/**
	 * 查询所有
	 * @return List<T>
	 */
	public List<T> list();

	/**
	 * 判断是否存在
	 * @param propertyName
	 * @param value
	 * @param id
	 * @return boolean
	 */
	public boolean isExist(String propertyName, String value, long id);
	
	public boolean ExecSQL(String sql);
	
	public List<T> findByExecSQL(StringBuffer querySql); 
}
