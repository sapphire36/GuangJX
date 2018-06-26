package org.kzcw.common.tools;
import java.util.LinkedList;
import java.util.List;

public class OpenLockList {
	// 关箱队列 单例模式,保证在全局可以访问
	public List<OpenMessage> list = new LinkedList<OpenMessage>();
	private static OpenLockList instance = new OpenLockList();
	public boolean IsFlush=false;

	public static OpenLockList getInstance() {
		return instance;
	}

	public void AddItem(OpenMessage message) {
		// 入队,有人申请开锁时添加该队列
		IsFlush=true; //执行更新
		list.add(message);
	}

	public OpenMessage DelItem(int index) {
		// 出队,管理人员打开锁时,将出队队头元素
		if(list.size()<=0) {
			return null;
		}
		if(index>list.size()||index<0) {
			return null;
		}else {
			IsFlush=true; //执行刷新
			return list.remove(index);
		}
	}
	
	public OpenMessage DelItemByEMEI(String emei) {
	    for(int i=0;i<list.size();i++) {
	    	if(list.get(i).EMEI.equals(emei)) {
	    		IsFlush=true; //执行刷新
	    		return list.remove(i);
	    	}
	    }
	    return null;
	}
	
	public OpenMessage getByIndex(int index) {
		if(list.size()<=0) {
			return null;
		}
		if(index>list.size()||index<0) {
			return null;
		}else {
			return list.get(index);
		}
	}

	public boolean IsEmpty() {
		// 判断队列是否为空
		return list.isEmpty();
	}
}
