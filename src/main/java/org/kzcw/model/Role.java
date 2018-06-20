package org.kzcw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kzcw.core.General;

@Entity
@Table(name="t_role")
public class Role extends General{
	//角色管理表
	
	private static final long serialVersionUID = 8724155022862447147L;
	private String RIGHTS;//权限
	
	@Column(nullable = false,length=100)
	public String getRIGHTS() {
		return RIGHTS;
	}
	public void setRIGHTS(String rIGHT) {
		RIGHTS = rIGHT;
	}
}
