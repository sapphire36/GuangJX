package org.kzcw.common.global;
import java.util.ArrayList;
import java.util.List;
import org.kzcw.model.Lightbox;

public class CheckLightBoxList {

    private static CheckLightBoxList instance=new CheckLightBoxList();
	public List<Lightbox> checklist = new ArrayList<Lightbox>();
	//这里蒋CheckMessage替换成LightBox,方便之后的存储
	
	public static CheckLightBoxList getInstance() {
		return instance;
	}
	
	public void addItem(Lightbox checkmessage) {
		// 入队,有人申请时添加该队列
		checklist.add(checkmessage);
	}

	public Lightbox CheckDelItem(int index) {
		// 出队,管理人员允许通过时,将出队队头元素
		if(checklist.size()<=0) {
			//没有数据
			return null;
		}
		if(index>checklist.size()||index<0) {
			//请求的index越界
			return null;
		}else {
			return checklist.remove(index);
		}
	}
	
	public Lightbox delItemByEMEI(String emei) {
	    for(int i=0;i<checklist.size();i++) {
	    	if(checklist.get(i).getIEME().equals(emei)) {
	    		return checklist.remove(i);
	    	}
	    }
	    return null;
	}
	
	public Lightbox getByIndex(int index) {
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
