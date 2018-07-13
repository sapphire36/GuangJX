package org.kzcw.common.global;

import java.util.LinkedList;
import java.util.List;
import org.kzcw.common.Iot.utils.CheckMessage;
import org.kzcw.model.Lightbox;

public class CheckLightBoxList {

    private static CheckLightBoxList instance=new CheckLightBoxList();
	public List<CheckMessage> checklist = new LinkedList<CheckMessage>();
	//List<Lightbox> list = null;
	public boolean IsFlush=true;
	
	
	public static CheckLightBoxList getInstance() {
		return instance;
	}
	
	
	public void AddItem(CheckMessage checkmessage) {
		// 入队,有人申请时添加该队列
		IsFlush=true; //执行更新
		checklist.add(checkmessage);
	}

	public CheckMessage CheckDelItem(int index) {
		// 出队,管理人员允许通过时,将出队队头元素
		if(checklist.size()<=0) {
			return null;
		}
		if(index>checklist.size()||index<0) {
			return null;
		}else {
			IsFlush=true; //执行刷新
			return checklist.remove(index);
		}
	}
	
	public CheckMessage CheckDelItemByEMEI(String emei) {
	    for(int i=0;i<checklist.size();i++) {
	    	if(checklist.get(i).EMEI.equals(emei)) {
	    		IsFlush=true; //执行刷新
	    		return checklist.remove(i);
	    	}
	    }
	    return null;
	}
	
	public CheckMessage getByIndex(int index) {
		if(checklist.size()<=0) {
			return null;
		}
		if(index>checklist.size()||index<0) {
			return null;
		}else {
			return checklist.get(index);
		}
	}

	public boolean IsEmpty() {
		// 判断队列是否为空
		return checklist.isEmpty();
	}
}
