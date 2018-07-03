package org.kzcw.common.Iot.youren;

import cn.usr.UsrCloudMqttCallbackAdapter;

public class ICallbackAdapter extends UsrCloudMqttCallbackAdapter {

	public boolean status = false;

	@Override
	public void onConnectAck(int returnCode, String description) {
		super.onConnectAck(returnCode, description);
		System.out.println("init " + returnCode);
		if (returnCode == 2) {
			System.out.println("连接成功!");
			status = true;
		}
	}

	@Override
	public void onSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
		System.out.println("订阅成功!");
		super.onSubscribeAck(messageId, clientId, topics, returnCode);

	}

	@Override
	public void onPublishDataAck(int messageId, String topic, boolean isSuccess) {
		// 推送确认
		super.onPublishDataAck(messageId, topic, isSuccess);
		/*
		if (isSuccess) {
			new Thread("线程1") {
				@Override
				public void run() {
					String IEME = getEMEIbyTopic(topic);
					WaitQueue que = WaitQueue.getInstance();
					OperaType type = que.GetItem(IEME);
					if (type != null) {
						OpenLockList olist = OpenLockList.getInstance();
						CloseLockList clist = CloseLockList.getInstance();
						if (type.type == 1) {
							OpenMessage message = olist.DelItemByEMEI(IEME);
							if (message != null) {
								CloseMessage close = new CloseMessage(message.USERNAME, message.EMEI);
								clist.AddItem(close);
							}
						} else {
							CloseMessage message = clist.DelItemByEMEI(IEME);
							if (message != null) {
								GradeMessge grade = new GradeMessge(message.USERNAME, message.EMEI);
								GradeList glist = GradeList.getInstance();
								glist.AddItem(grade);
							}
						}
					}
				}
			}.start();

		}
		System.out.println("推送确认" + topic + isSuccess);
		*/
	}

	public String getEMEIbyTopic(String topic) {
		String ret = "";
		for (int i = 0; i < topic.length(); i++) {
			if ((topic.charAt(i) > '0') && (topic.charAt(i) < '9')) {
				ret += topic.charAt(i);
			}
		}
		return ret;
	}

	@Override
	public void onPublishDataResult(int messageId, String topic) {
		// 推送结果
		super.onPublishDataResult(messageId, topic);
		// System.out.println("推送结果"+topic);
	}

	@Override
	public void onReceiveEvent(int messageId, String topic, byte[] data) {
		super.onReceiveEvent(messageId, topic, data);
	}
}
