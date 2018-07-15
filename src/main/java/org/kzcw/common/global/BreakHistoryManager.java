package org.kzcw.common.global;
import java.util.HashMap;
import java.util.Map;

import org.kzcw.common.Iot.youren.OperaType;
import org.kzcw.model.Breakhistory;


public class BreakHistoryManager {
    //单例模式 确保整个程序内都可以访问
	public static BreakHistoryManager instance=new BreakHistoryManager();
	//hashmap 提高查询速度
	private Map<String,Breakhistory> list = new HashMap<String,Breakhistory>();
	private String current;//当前报警
	
	public static BreakHistoryManager getInstance() {
		//获取实例
		return instance;
	}
	
	public void addItem(String ieme,Breakhistory content) {
		//添加项目
		Breakhistory temp=getItem(ieme);
		if(temp!=null) {
			//如果存在该IEME记录的报警
			if(!temp.getTYPE().equals(content.getTYPE())) {
				//如果存在该IEME,但上报记录不一致
				list.put(ieme,content);
				current=ieme;
			}
		}else {
			//如果不存在该IEME记录的报警
			list.put(ieme,content);
			current=ieme;
		}
	}
	
	public Breakhistory getItem(String emei) {
		return list.get(emei);
	}
	
	public String getCurrentBreakMessage() {
		String retstring="";
		Breakhistory ret=list.get(current);
		if(ret!=null) {
			retstring=ret.getIEME()+":"+ret.getTYPE();
		}else {
			retstring="异常";
		}
		return retstring;
	}
	
	public Map<String,Breakhistory> getMapEntrySet(){
		//返回MapSet
		return list;
	}
}
