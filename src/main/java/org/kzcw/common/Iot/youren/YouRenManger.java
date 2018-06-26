package org.kzcw.common.Iot.youren;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service("yourenmanager")
public class YouRenManger {
	//有人物联网控制端
	private ClientAdapter clientAdapter = new ClientAdapter();
	private ICallbackAdapter clinetCallbackAdapter = new ICallbackAdapter();
	String name = "李环宇";
	String passwd = "xz86512121";
	byte[] opendata = { (byte) 0xA5, 0x06, 0x00, 0x20, 0x00, 0x01, 0x50, (byte) 0xE4 };
	byte[] closedata = { (byte) 0xA5, 0x06, 0x00, 0x21, 0x00, 0x01, 0x01, 0x24 };
	public static YouRenManger instance=new YouRenManger();
	List<String> devicelist=new ArrayList<String>();

	public YouRenManger() {
		// TODO Auto-generated constructor stub
		Init();
	}
	
	public static YouRenManger getInstance() {
		return instance;
	}
	
	private void Init() {
		InitDevice();
		try {
			doConnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doConnect() throws MqttException {
 
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
	      for(int i=0;i<devicelist.size();i++) {
	    	  clientAdapter.SubscribeForDevId(devicelist.get(i));
	      }
	}
	
	public void InitDevice() {
		devicelist.add("356566078008224");
		devicelist.add("356566077995884");
		devicelist.add("356566077983401");
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
