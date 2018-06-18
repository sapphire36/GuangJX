package org.kzcw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kzcw.core.General;


@Entity
@Table(name="t_lockdevice")
public class lockdevice extends General{
    //锁信息表
	private static final long serialVersionUID = -1666940818328212382L;
	
	private long LOCKID;//锁编号
	private String IMEI;//设备IMEI编号
	private String PSK;//PSK码
	private String SPEC;//规格
	
	@Column(nullable = false,length=20)
	public long getLOCKID() {
		return LOCKID;
	}
	public void setLOCKID(long lOCKID) {
		LOCKID = lOCKID;
	}
	
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
