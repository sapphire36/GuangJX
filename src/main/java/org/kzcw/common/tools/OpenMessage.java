package org.kzcw.common.tools;

public class OpenMessage  extends BaseMessage{
    //开锁列表消息
	public OpenMessage(String uid,String emei) {
		// TODO Auto-generated constructor stub
		super(uid, emei);
	}
	
	public void setReady() {
		IsReady=true;
	}
}
