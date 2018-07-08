package org.kzcw.model;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.kzcw.core.General;

@Entity
@Table(name="t_lightbox")
public class Lightbox extends General{
    //光交箱信息表
	
	private static final long serialVersionUID = -4654940818328212382L;
	
	private String SPEC;//规格
	private String MADETYPE;//厂家型号
	private String LOCATION; //安装位置 格式如:75.15,562.2
	private String IEME;//锁编号
	private String NAME;//箱体名称（客户自定义箱体名）
	private String PEOPLE;//安装人员
	private Blob PHOTO; //安装时照片
	private int ISREGIST;//是否注册:1表示已经注册   0表示未注册
	private String USERNAME;//用户名
	
	
	@Column(nullable = false,length=50)
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	@Column(nullable = true,columnDefinition="INT default 0")
	public int getISREGIST() {
		return ISREGIST;
	}
	public void setISREGIST(int iSREGIST) {
		ISREGIST = iSREGIST;
	}
	
	@Column(nullable = true,length=50)
	public String getMADETYPE() {
		return MADETYPE;
	}
	public void setMADETYPE(String mADETYPE) {
		MADETYPE = mADETYPE;
	}
	
	@Column(nullable = true,length=50)
	public String getPEOPLE() {
		return PEOPLE;
	}
	public void setPEOPLE(String pEOPLE) {
		PEOPLE = pEOPLE;
	}
	
	@Column(nullable = true,length=50)
	public String getNAME() {
		return NAME;
	}
	
	public void setNAME(String nAME) {
		NAME = nAME;
	}	
	
	@Column(nullable = true,length=50)
	public String getSPEC() {
		return SPEC;
	}
	public void setSPEC(String sPEC) {
		SPEC = sPEC;
	}
	
	@Column(nullable = false,length=50)
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	
	@Column(nullable = false,length=50)
	public String getIEME() {
		return IEME;
	}
	public void setIEME(String iEME) {
		IEME = iEME;
	}
	@Column(nullable = true)
	public Blob getPHOTO() {
		return PHOTO;
	}
	public void setPHOTO(Blob pHOTO) {
		PHOTO = pHOTO;
	}
}

