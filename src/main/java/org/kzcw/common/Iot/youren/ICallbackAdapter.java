package org.kzcw.common.Iot.youren;

import java.math.BigInteger;


import cn.usr.UsrCloudMqttCallbackAdapter;

public class ICallbackAdapter extends UsrCloudMqttCallbackAdapter {

	public boolean status = false;
	//@Autowired
	//StatusService service;

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
		String newstring = bytes2hex01(data);
		String subnewstring = newstring.substring(0, newstring.length() - 4);
		String subendstring = newstring.substring(newstring.length() - 4, newstring.length());
		String finalstring = CRC16.checkcode(subnewstring);
		if (subendstring.equals(finalstring) == true) {
			if (newstring.length() > 45) {
				ResultData newdevice = analysisdata(newstring);
				newdevice.DeviceID = topic.substring(topic.length() - 15, topic.length());
				WaitQueue que = WaitQueue.getInstance();
				/*
				try {
					Status status=new Status();
					status.setIEME(newdevice.DeviceID);
					if(newdevice.eleclock) {
						status.setSTATUS("开");
					}
					
					if(newdevice.elecunlock) {
						status.setSTATUS("关");
					}
					status.setTEMPERATURE(String.valueOf(newdevice.temperature));
					status.setVOLTAGE(String.valueOf(newdevice.volt));
					service.save(status);
				} catch (Exception e) {
					// TODO: handle exception
				}
				*/
				que.doOperate(newdevice.DeviceID); //执行操作
				System.out.println("接收到数据" + newdevice.DeviceID);
				System.out.println(checkstatus(newdevice));
			}
		} else
			System.out.println("失败");
		// System.out.println(newstring);
	}
	
	private String checkstatus(ResultData data) {
		String result=":";
		if(data.volt<3.1) {
			result=result+"电压过低  ";
		}
		if((data.elecunlock==false)&&(data.eleclock==true)&&(data.handlock=false)) {
			result=result+"非法或应急开锁 ";
		}
		if(!(data.eleclock^data.elecunlock)) {
			result=result+"机械故障 ";
		}
		if(data.temperature<-30) {
			 result=result+"温度过低 ";
		}
			 	
		if(data.temperature>85) {
			 result=result+"温度过高 ";
		}
		 
        if(result.equals(":")) {
        	result="正常";
        }
        return result;
	}

	public static String bytes2hex01(byte[] bytes) {
		BigInteger bigInteger = new BigInteger(1, bytes);
		return bigInteger.toString(16);
	}

	public ResultData analysisdata(String receivecode) {
		ResultData device = new ResultData();
		String volthex = receivecode.substring(38, 42);
		device.volt = dealwithvolt(volthex);
		String temphex = receivecode.substring(42, 46);
		device.temperature = dealwithtemperature(temphex);
		String allforlock = receivecode.substring(37, 38);
		char lockcode = allforlock.charAt(0);
		String lockbinary = Hex2Binary(lockcode);
		char elecunlockcode = lockbinary.charAt(3);
		if (elecunlockcode == '0')
			device.elecunlock = false;
		else
			device.elecunlock = true;
		char eleclockcode = lockbinary.charAt(2);
		if (eleclockcode == '0')
			device.eleclock = false;
		else
			device.eleclock = true;
		char handlockcode = lockbinary.charAt(1);
		if (handlockcode == '0')
			device.handlock = false;
		else
			device.handlock = true;
		return device;
	}

	public float dealwithtemperature(String temphex) {
		float temp = Integer.parseInt(temphex, 16);
		float finaltemp = (float) (temp * 0.1 - 50);
		return finaltemp;
	}

	public float dealwithvolt(String volthex) {
		float volt = Integer.parseInt(volthex, 16);
		float finalvolt = (float) (volt * 0.01);
		return finalvolt;
	}

	private String Hex2Binary(char charAt) {
		// TODO Auto-generated method stub
		switch (charAt) {
		case '0':
			return "0000";
		case '1':
			return "0001";
		case '2':
			return "0010";
		case '3':
			return "0011";
		case '4':
			return "0100";
		case '5':
			return "0101";
		case '6':
			return "0110";
		case '7':
			return "0111";
		case '8':
			return "1000";
		case '9':
			return "1001";
		case 'a':
			return "1010";
		case 'b':
			return "1011";
		case 'c':
			return "1100";
		case 'd':
			return "1101";
		case 'e':
			return "1110";
		case 'f':
			return "1111";

		}
		return null;
	}
}
