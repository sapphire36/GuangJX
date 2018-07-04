package org.kzcw.common.Iot.youren;

import org.kzcw.common.global.ParsingReceiveQueue;
import org.kzcw.common.global.SysLogManager;

import cn.usr.UsrCloudMqttCallbackAdapter;

public class ICallbackAdapter extends UsrCloudMqttCallbackAdapter {

	public boolean status = false;
	ParsingReceiveQueue parsingmanager=ParsingReceiveQueue.getInstance();
	SysLogManager logmanager=SysLogManager.getInstance();

	@Override
	public void onConnectAck(int returnCode, String description) {
		super.onConnectAck(returnCode, description);
		logmanager.addLog("正在初始化服务器"+returnCode, 1);//添加日志
		if (returnCode == 2) {
			logmanager.addLog("连接成功!"+returnCode, 1);//添加日志
			status = true;
		}
	}

	@Override
	public void onSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
		logmanager.addLog("订阅成功!"+returnCode, 1);//添加日志
		super.onSubscribeAck(messageId, clientId, topics, returnCode);

	}

	@Override
	public void onPublishDataAck(int messageId, String topic, boolean isSuccess) {
		// 推送确认
		super.onPublishDataAck(messageId, topic, isSuccess);
		logmanager.addLog("推送确认"+topic, 1);//添加日志	
	}

	@Override
	public void onPublishDataResult(int messageId, String topic) {
		// 推送结果
		super.onPublishDataResult(messageId, topic);
		logmanager.addLog("推送结果"+topic, 1);//添加日志	
		// System.out.println("推送结果"+topic);
	}

	@Override
	public void onReceiveEvent(int messageId, String topic, byte[] data) {
		super.onReceiveEvent(messageId, topic, data);
		//进行解析
		parsingmanager.AddMessage(new ReceiveData(messageId, topic, data));
		logmanager.addLog("收到上报数据"+topic, 1);//添加日志	
	}
	
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
