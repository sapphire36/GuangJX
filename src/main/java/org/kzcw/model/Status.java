package org.kzcw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.kzcw.core.General;

@Entity
@Table(name="t_status")
public class Status  extends General{
	
	private static final long serialVersionUID = -4654940818328212372L;
	private String NAME;//光交箱名称
	private String VOLTAGE;//电池电压
	private String TEMPERATURE;//机箱温度
	private String STATUS;//锁状态
	
	@Column(nullable = true,length=50)
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	@Column(nullable = true,length=50)
	public String getVOLTAGE() {
		return VOLTAGE;
	}
	public void setVOLTAGE(String vOLTAGE) {
		VOLTAGE = vOLTAGE;
	}
	
	@Column(nullable = true,length=50)
	public String getTEMPERATURE() {
		return TEMPERATURE;
	}
	public void setTEMPERATURE(String tEMPERATURE) {
		TEMPERATURE = tEMPERATURE;
	}
	
	@Column(nullable = true,length=50)
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	
	

}
