package org.kzcw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kzcw.core.General;


@Entity
@Table(name="t_lockdevice")
public class Lockdevice extends General{
    //锁信息表
	private static final long serialVersionUID = -1666940818328212382L;
	
	private String IMEI;//设备IMEI编号
	private String PSK;//PSK码
	private String SPEC;//规格
	
	
	@Column(nullable = false,length=50)
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	
	@Column(nullable = true,length=50)
	public String getPSK() {
		return PSK;
	}
	public void setPSK(String pSK) {
		PSK = pSK;
	}
	
	@Column(nullable = true,length=50)
	public String getSPEC() {
		return SPEC;
	}
	public void setSPEC(String sPEC) {
		SPEC = sPEC;
	}
}
