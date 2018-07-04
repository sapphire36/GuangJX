package org.kzcw.common.global;

import java.util.ArrayList;
import java.util.List;

import org.kzcw.model.Systemlogs;

public class SysLogManager {
	//日志类
	
	private List<Systemlogs> loglist;
	private static SysLogManager instance=new SysLogManager();
	
	public SysLogManager() {
		// TODO Auto-generated constructor stub
		loglist=new ArrayList<Systemlogs>();
	}
	
	public static SysLogManager getInstance() {
		//获取实例
		return instance;
	}
	
	public void addLog(String content,int type) {
		//type 1:代表消息  2:代表警告  3:代表故障
		loglist.add(new Systemlogs(content,String.valueOf(type)));
	}
	
	public Systemlogs getFirstElement() {
		//判断是否为空
		if(loglist.size()>0) {
			return loglist.get(0);
		}else {
			return null;
		}
	}
	
	public boolean IsEmpty() {
		//判断是否为空
		if(loglist.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public  List<Systemlogs> getLogList() {
		//返回日志列表
		return loglist;
	}
}
