package org.kzcw.common.tools;

import java.util.LinkedList;
import java.util.Queue;

public class CloseLockQueue {
	// 关箱队列 单例模式,保证在全局可以访问

	Queue<ControlMessage> queue = new LinkedList<ControlMessage>();
	private static CloseLockQueue instance = new CloseLockQueue();

	public static CloseLockQueue getInstance() {
		return instance;
	}

	public void InQueue(ControlMessage message) {
		// 入队,有人申请开锁时添加该队列
		queue.offer(message);
	}

	public ControlMessage OutQueue() {
		// 出队,管理人员打开锁时,将出队队头元素
		if (IsEmpty()) {
			return null;
		} else {
			ControlMessage message = queue.element();
			
			//当开锁队列,被开锁时,将开锁队列元素添加到关锁队列当中
			GradeQueue grandequeue=GradeQueue.getInstance();
			grandequeue.InQueue(message);
			//返回请求消息
			return message;
		}
	}

	public boolean IsEmpty() {
		// 判断队列是否为空
		return queue.isEmpty();
	}
}