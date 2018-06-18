package org.kzcw.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.kzcw.core.Model;

/**
 * Copyright 2013 - 2014
 * @project xu － AbstractModel
 * @author xu
 * @version 2014年3月22日下午11:41:34 - AbstractModel.java
 */
@MappedSuperclass
public abstract class General extends Model {

	private static final long serialVersionUID = -6966387387911439572L;

	private long id;

	/**
	 * 操作人员
	 */
	private String operation;

	/**
	 * 创建时间
	 */
	private Date addtime;


	@Column(nullable = false, length = 40)
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * 获得属性 operation 的值
	 * @return String
	 */
	@Column(nullable = true, length = 50)
	public String getOperation() {
		return operation;
	}

	/**
	 * 为属性 operation 赋值
	 * @param operation String
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
