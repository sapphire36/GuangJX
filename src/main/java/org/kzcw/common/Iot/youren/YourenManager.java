package org.kzcw.common.Iot.youren;

import org.eclipse.paho.client.mqttv3.MqttException;

public class YourenManager {

	public void SubscribeForDevIds() throws MqttException {

		  /* 1.��ʼ���ͻ������� */
		  ClientAdapter clientAdapter = new ClientAdapter();

		  /* 2.��ʼ���ͻ��˻ص����� */
		  ICallbackAdapter clinetCallbackAdapter = new ICallbackAdapter();

		
		  /* 3.�ͻ������ûص� */
		  clientAdapter.setUsrCloudMqttCallback(clinetCallbackAdapter);

		  /* 4.�������� */
		  clientAdapter.Connect("李环宇", "xz86512121");
		  
		  try {
				Thread.sleep(3000);//����2��
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  /* 5.������Ϣ(�����豸)*/
		 clientAdapter.SubscribeForDevId("356566077995884");
		
	}
	
	
	private static void publishForDevIdunlock() throws MqttException{
		  /* 1.��ʼ���ͻ������� */
		  ClientAdapter clientAdapter = new ClientAdapter();

		  /* 2.��ʼ���ͻ��˻ص����� */
		  ICallbackAdapter clinetCallbackAdapter = new ICallbackAdapter();
	 
		
		  /* 3.�ͻ������ûص� */
		  clientAdapter.setUsrCloudMqttCallback(clinetCallbackAdapter);

		  /* 4.�������� */
		  clientAdapter.Connect("李环宇", "xz86512121");
		  while(true){
			  if(clinetCallbackAdapter.status){
				  break;
			  }
			  try {
					Thread.sleep(2000);//����2��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  //clientAdapter.SubscribeForDevId("356566077995884");
		  //����
		  byte[] data={(byte)0xA5,0x06,0x00,0x21,0x00,0x01,0x01,0x24};
		  try {
				Thread.sleep(200);//����0.2��
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  clientAdapter.publishForDevId("356566077995884", data);
	}
	
	private void publishForDevId() throws MqttException{
		  /* 1.��ʼ���ͻ������� */
		  ClientAdapter clientAdapter = new ClientAdapter();

		  /* 2.��ʼ���ͻ��˻ص����� */
		  ICallbackAdapter clinetCallbackAdapter = new ICallbackAdapter();
	 
		
		  /* 3.�ͻ������ûص� */
		  clientAdapter.setUsrCloudMqttCallback(clinetCallbackAdapter);

		  /* 4.�������� */
		  clientAdapter.Connect("李环宇", "xz86512121");
		  while(true){
			  if(clinetCallbackAdapter.status){
				  break;
			  }
			  try {
					Thread.sleep(2000);//����2��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  //clientAdapter.SubscribeForDevId("356566077995884");
		  //����
		  byte[] data={(byte)0xA5,0x06,0x00,0x20,0x00,0x01,0x50,(byte)0xE4};
		  try {
				Thread.sleep(200);//����0.2��
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  clientAdapter.publishForDevId("356566077995884", data);
	}
	
	public void OpenLock() {
		try {
			//������Ϣ
			SubscribeForDevIds();
			//����ָ��
			while(true)
			{
			publishForDevId();
			
			try {
					Thread.sleep(20000);//����20��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			GlobelData gdata=GlobelData.getInstance();
			System.out.println("�����"+gdata.newstring);
			try {
				Thread.sleep(2000);//����2��
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			if(gdata.newstring.equals("a5060020c331"))
				{break;}
			}
			
			//����ָ��
			while(true)
			{
			   publishForDevIdunlock();
			
			
			try {
					Thread.sleep(20000);//����20��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			GlobelData gdata=GlobelData.getInstance();
			System.out.println("�����"+gdata.newstring);
			try {
				Thread.sleep(2000);//����2��
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			if(gdata.newstring.equals("a506002102f1"))
				{break;}
			}
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
