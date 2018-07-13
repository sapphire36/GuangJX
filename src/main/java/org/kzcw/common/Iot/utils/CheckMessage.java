package org.kzcw.common.Iot.utils;

public class CheckMessage extends BaseMessage{
    //审核列表消息
	public String NAME;//用户名字
	public String LOCATION;//开锁位置
	public String IEME;
	public boolean IsReady=false;//是否准备发送
	
	public CheckMessage(String name,String ieme,String location) {
		// TODO Auto-generated constructor stub
		this.NAME=name;
		this.IEME=ieme;
		this.LOCATION=location;
	}
	
	
	public CheckMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public void setReady() {
		IsReady=true;
	}
}