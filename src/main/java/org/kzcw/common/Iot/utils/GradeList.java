package org.kzcw.common.Iot.utils;

import java.util.LinkedList;
import java.util.List;

public class GradeList {

	public List<GradeMessge> list = new LinkedList<GradeMessge>();
	private static GradeList instance = new GradeList();
	public boolean IsFlush=false;

	public static GradeList getInstance() {
		return instance;
	}
	
	public void AddItem(GradeMessge message) {
		// 入队,关锁后,将消息添加到待评价队列
		IsFlush=true; //执行更新
		list.add(message);
	}

	public GradeMessge DelItem(int index) {
		// 出队,管理人员评价结束后,将出队头元素
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
	
	public GradeMessge getByIndex(int index) {
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
