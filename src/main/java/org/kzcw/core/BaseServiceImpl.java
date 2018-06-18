package org.kzcw.core;

import java.io.Serializable;
/**
 * 业务逻辑基础实现类
 * Copyright 2013 - 2014
 * @project pdkj － BaseServiceImpl
 * @author pdkj
 * @version 2014年3月22日下午11:41:27 - BaseServiceImpl.java
 */
public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {


	protected BaseDao<T> dao;

	public void setBaseDao(BaseDao<T> dao) {
		this.dao = dao;
	}
}
