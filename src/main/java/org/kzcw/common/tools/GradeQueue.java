package org.kzcw.common.tools;

import java.util.LinkedList;
import java.util.Queue;

public class GradeQueue {

	public Queue<ControlMessage> queue = new LinkedList<ControlMessage>();
	private static GradeQueue instance = new GradeQueue();
	public boolean IsFlush=false;

	public static GradeQueue getInstance() {
		return instance;
	}

	public void InQueue(ControlMessage message) {
		// 入队,关锁后,将消息添加到待评价队列
		IsFlush=true; //执行更新
		queue.offer(message);
	}

	public ControlMessage OutQueue() {
		// 出队,管理人员评价结束后,将出队头元素
		if (IsEmpty()) {
			return null;
		} else {
			ControlMessage message = queue.element();
			
			//当开锁队列,被开锁时,将开锁队列元素添加到关锁队列当中
			CloseLockQueue closequeue=CloseLockQueue.getInstance();
			closequeue.InQueue(message);
			IsFlush=true; //执行更新
			//返回请求消息
			return message;
		}
	}

	public boolean IsEmpty() {
		// 判断队列是否为空
		return queue.isEmpty();
	}
}
