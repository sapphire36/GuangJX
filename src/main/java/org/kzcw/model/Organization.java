package org.kzcw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kzcw.core.General;

@Entity
@Table(name="t_organization")
public class Organization extends General{
	//施工单位信息表
	
	
	private static final long serialVersionUID = -165494081832825L;

	private String ONAME;//单位名称
	private String ADDRESS;//公司地址
	private String TEL;//电话
	private String DEPOSIT;//押金
	private int STATUS; //状态,1表示激活,0表示未激活
	
	@Column(nullable = false,length=50)
	public String getONAME() {
		return ONAME;
	}
	public void setONAME(String oNAME) {
		ONAME = oNAME;
	}
	
	@Column(nullable = true,length=50)
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	
	@Column(nullable = true,length=20)
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	
	@Column(nullable = true,length=20)
	public String getDEPOSIT() {
		return DEPOSIT;
	}
	public void setDEPOSIT(String dEPOSIT) {
		DEPOSIT = dEPOSIT;
	}
	
	@Column(nullable = false,length=8)
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
}

