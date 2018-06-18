package org.kzcw.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.kzcw.core.General;

@Entity
@Table(name="t_lightbox")
public class lightbox extends General{
    //光交箱信息表
	
	private static final long serialVersionUID = -4654940818328212382L;
	
	private long BOXID;//箱体编号
	private String SPEC;//规格
	private String MADEADDRESS;//出产地
	private String LOCATION; //安装位置 格式如:75.15,562.2
	private long LOCKID;//锁编号
	
	@Column(nullable = false,length=20)
	public long getLID() {
		return BOXID;
	}
	public void setLID(long lID) {
		BOXID = lID;
	}
	
	@Column(nullable = true,length=50)
	public String getSPEC() {
		return SPEC;
	}
	public void setSPEC(String sPEC) {
		SPEC = sPEC;
	}
	
	@Column(nullable = true,length=50)
	public String getMADEADDRESS() {
		return MADEADDRESS;
	}
	public void setMADEADDRESS(String mADEADDRESS) {
		MADEADDRESS = mADEADDRESS;
	}
	
	@Column(nullable = false,length=50)
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	
	@Column(nullable = true,length=20)
	public long getLOCKID() {
		return LOCKID;
	}
	public void setLOCKID(long lOCKID) {
		LOCKID = lOCKID;
	}
}

