package org.kzcw.common.global;
import java.util.HashMap;
import java.util.Map;

import org.kzcw.common.Iot.youren.OperaType;
public class WaitPublishQueue {
	//等待推送队列
    //单例模式 确保整个程序内都可以访问
	public static WaitPublishQueue instance=new WaitPublishQueue();
	//hashmap 提高查询速度
	private Map<String,OperaType> list = new HashMap<String,OperaType>();
	public boolean IsFlush;  //是否刷新
	
	public static WaitPublishQueue getInstance() {
		//获取实例
		return instance;
	}
	
	public Map<String,OperaType> getMapEntrySet(){
		//返回MapSet
		return list;
	}
	
	public void AddItem(OperaType type) {
		//添加项目
		IsFlush=true;
		list.put(type.EMEI,type);
	}
	
	public void DelItem(String emei) {
		//删除项目
		IsFlush=true;
		list.remove(emei);
	}
	
	public OperaType GetItem(String emei) {
		return list.get(emei);
	}
	
	public void doOperate(String EMEI) {
		//执行操作
		if(list.get(EMEI)!=null) {
			//如果在操作列表中存在改项
			IsFlush=true;
			OperaType opera=list.get(EMEI);
			if(opera.type==1) {
				//执行开锁
				YouRenManger manager=YouRenManger.getInstance();
				manager.doOpenLock(EMEI);
				manager.doOpenLock(EMEI);
				DelItem(EMEI);
			}else {
				//执行关锁
				YouRenManger manager=YouRenManger.getInstance();
				manager.doCloseLock(EMEI);
				manager.doCloseLock(EMEI);
				DelItem(EMEI);
			}
		}
	}
}
