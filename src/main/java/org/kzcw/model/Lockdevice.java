package org.kzcw.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.kzcw.core.General;


@Entity
@Table(name="t_lockdevice")
public class Lockdevice extends General{
    //NB-IoT锁信息表
	private static final long serialVersionUID = -1666940818328212382L;
	
	private String NAME;//设备名称
	private String IMEI;//设备IMEI编号
	private String PSK;//PSK码
	private String SPEC;//规格
	private int ISONLINE;//1 表示在线   0表示下线
	private int STATUS;//状态 0:关闭  1:打开  2:故障
	private int ISREGIST;//是否注册:1表示已经注册   0表示未注册
	
	
	
	@Column(nullable = false,length=50)
	public String getNAME() {
		return NAME;
	}
	
	public void setNAME(String nAME) {
		NAME = nAME;
	}

	@Column(nullable = true)
	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
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

	@Column(nullable = true)
	public int getISONLINE() {
		return ISONLINE;
	}

	public void setISONLINE(int iSONLINE) {
		ISONLINE = iSONLINE;
	}
	
	@Column(nullable = true)
	public int getISREGIST() {
		return ISREGIST;
	}

	public void setISREGIST(int iSREGIST) {
		ISREGIST = iSREGIST;
	}
}
