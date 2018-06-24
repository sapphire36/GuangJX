package org.kzcw.common.Iot.youren;
import java.util.HashMap;
import java.util.Map;
public class WaitQueue {
    //单例模式 确保整个程序内都可以访问
	public static WaitQueue instance=new WaitQueue();
	//hashmap 提高查询速度
	private Map<String,OperaType> list = new HashMap<String,OperaType>();
	
	public static WaitQueue getInstance() {
		//获取实例
		return instance;
	}
	
	public void AddItem(OperaType type) {
		//添加项目
		list.put(type.EMEI,type);
	}
	
	public void DelItem(String emei) {
		//删除项目
		list.remove(emei);
	}
	
	public void doOperate(String EMEI) {
		//执行操作
		if(list.get(EMEI)!=null) {
			//如果在操作列表中存在改项
			OperaType opera=list.get(EMEI);
			if(opera.type==1) {
				//执行开锁
				YouRenManger manager=YouRenManger.getInstance();
				manager.doOpenLock(EMEI);
			}else {
				//执行关锁
				YouRenManger manager=YouRenManger.getInstance();
				manager.doCloseLock(EMEI);
			}
		}
	}
}
