package org.kzcw.common.Iot.youren;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.kzcw.common.utils.SystemData;

//Component作用详见https://blog.csdn.net/m0_37626813/article/details/78558010
public class YouRenManger {
	//有人物联网控制端
	private ClientAdapter clientAdapter = new ClientAdapter();
	private ICallbackAdapter clinetCallbackAdapter = new ICallbackAdapter();
	String name = "李环宇";
	String passwd = "xz86512121";
	byte[] opendata = { (byte) 0xA5, 0x06, 0x00, 0x20, 0x00, 0x01, 0x50, (byte) 0xE4 };
	byte[] closedata = { (byte) 0xA5, 0x06, 0x00, 0x21, 0x00, 0x01, 0x01, 0x24 };
	public static YouRenManger instance=new YouRenManger();

	public YouRenManger() {
		// TODO Auto-generated constructor stub
		//doStart();
	}
	
	public static YouRenManger getInstance() {
		return instance;
	}
	
	public void doStart() {
		try {
			doConnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doConnect() throws MqttException {
 
		  clientAdapter.setUsrCloudMqttCallback(clinetCallbackAdapter);
		  /* 4.进行连接 */
		  clientAdapter.Connect(name,passwd);
		  int dotry=2;
	      while (dotry>0) {
				if (clinetCallbackAdapter.status) {
					break;
				}
				dotry--;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		  /* 5.订阅消息(单个设备)*/
	      SystemData data=SystemData.getInstance(); //获取系统数据
	      if(data.locklist!=null) {
		      for(String emei:data.locklist) {
		    	   try {
		    		  clientAdapter.SubscribeForDevId(emei);
				    } catch (MqttException e) {
					// TODO: handle exception
				    }
		      }
	      }
	}
	
	public boolean doOpenLock(String emei) {
		try {
			clientAdapter.publishForDevId(emei, opendata);
			return true;
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean doCloseLock(String emei) {
		try {
			clientAdapter.publishForDevId(emei, closedata);
			return true;
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
