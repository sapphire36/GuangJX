package org.kzcw.model;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.kzcw.core.General;

@Entity
@Table(name="t_operatehistory")
public class Operatehistory extends General{
	//操作历史表
	
	private static final long serialVersionUID = -3254940818328212382L;

	private long BOXID;//光交箱体编号
	private long ORGANIZATIONID;//施工单位编号
	private Blob BEFOR_MAINTAIN; //维修前照片
	private Blob AFTER_MAINTAIN; //维修后照片
	private Blob FIN_MAINTAIN; //关箱后照片
	private int SCORE;//评分
	
	
	@Column(nullable = false,length=20)
	public long getBOXID() {
		return BOXID;
	}
	public void setBOXID(long bOXID) {
		BOXID = bOXID;
	}
	
	@Column(nullable = false,length=20)
	public long getORGANIZATIONID() {
		return ORGANIZATIONID;
	}
	public void setORGANIZATIONID(long oRGANIZATIONID) {
		ORGANIZATIONID = oRGANIZATIONID;
	}
	
	@Column(nullable = true)
	public Blob getBEFOR_MAINTAIN() {
		return BEFOR_MAINTAIN;
	}
	public void setBEFOR_MAINTAIN(Blob bEFOR_MAINTAIN) {
		BEFOR_MAINTAIN = bEFOR_MAINTAIN;
	}
	
	@Column(nullable = true)
	public Blob getAFTER_MAINTAIN() {
		return AFTER_MAINTAIN;
	}
	public void setAFTER_MAINTAIN(Blob aFTER_MAINTAIN) {
		AFTER_MAINTAIN = aFTER_MAINTAIN;
	}
	
	@Column(nullable = true)
	public Blob getFIN_MAINTAIN() {
		return FIN_MAINTAIN;
	}
	public void setFIN_MAINTAIN(Blob fIN_MAINTAIN) {
		FIN_MAINTAIN = fIN_MAINTAIN;
	}
	
	@Column(nullable = false)
	public int getSCORE() {
		return SCORE;
	}
	public void setSCORE(int sCORE) {
		SCORE = sCORE;
	}
}

