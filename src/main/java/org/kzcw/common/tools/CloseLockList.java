package org.kzcw.common.tools;
import java.util.LinkedList;
import java.util.List;

public class CloseLockList {
	// 关箱队列 单例模式,保证在全局可以访问

	public List<CloseMessage> list = new LinkedList<CloseMessage>();
	private static CloseLockList instance = new CloseLockList();
	public boolean IsFlush=false;

	public static CloseLockList getInstance() {
		return instance;
	}

	public void AddItem(CloseMessage message) {
		// 入队,有人申请关闭锁时添加该队列
		IsFlush=true; //执行更新
		list.add(message);
	}

	public CloseMessage DelItem(int index) {
		// 出队,管理人员打开锁时
		if(index>list.size()||index<0) {
			return null;
		}else {
			IsFlush=true; //执行刷新
			return list.remove(index);
		}
	}

	public boolean IsEmpty() {
		// 判断队列是否为空
		return list.isEmpty();
	}
}