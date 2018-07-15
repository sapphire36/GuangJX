package org.kzcw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kzcw.core.General;
@Entity
@Table(name="t_area")
public class Area extends General{
    
	//有人网络连接信息
	private static final long serialVersionUID = -188992185943354063L;

	private String AREANAME;//区域名
	private String NAME; //该区域有人网登录名
	private String PASSWD;//该区域有人网登录密码
	
	
	@Column(nullable = false,length=50)
	public String getAREANAME() {
		return AREANAME;
	}
	public void setAREANAME(String aREANAME) {
		AREANAME = aREANAME;
	}
	
	@Column(nullable = false,length=50)
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	@Column(nullable = false,length=50)
	public String getPASSWD() {
		return PASSWD;
	}
	public void setPASSWD(String pASSWD) {
		PASSWD = pASSWD;
	}
}
