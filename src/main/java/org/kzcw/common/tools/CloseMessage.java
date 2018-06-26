package org.kzcw.common.tools;

public class CloseMessage extends BaseMessage{
	//关闭列表消息
	
	public CloseMessage(String uid,String emei) {
		// TODO Auto-generated constructor stub
		super(uid, emei);
	}
	
	public void setReady() {
		IsReady=true;
	}
}
