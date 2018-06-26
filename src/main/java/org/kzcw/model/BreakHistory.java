package org.kzcw.model;
import javax.persistence.Column;
import org.kzcw.core.General;
public class BreakHistory extends General{
	
	//故障信息表
	private static final long serialVersionUID = -8342801746053290192L;
	
	private String IEME;//设备ID
	private String TYPE;//故障原因
	
	@Column(nullable = false,length=50)
	public String getIEME() {
		return IEME;
	}
	public void setIEME(String iEME) {
		IEME = iEME;
	}
	
	@Column(nullable = false,length=50)
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
}
