package org.kzcw.common.Iot.youren;

import java.math.BigInteger;
import cn.usr.UsrCloudMqttCallbackAdapter;

public class ICallbackAdapter extends UsrCloudMqttCallbackAdapter {
    
	public boolean status=false;
    @Override
  	public void onConnectAck(int returnCode, String description) {
  		super.onConnectAck(returnCode, description);
  		System.out.println("init "+returnCode);
  		if(returnCode==2){
  			status=true;
  		}
  	}
    
	@Override
	public void onSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
		super.onSubscribeAck(messageId, clientId, topics,returnCode);
		System.out.println("����"+returnCode+"messageId"+messageId+"topics"+topics+"clientId"+clientId);
	}
	
	@Override
	public void onPublishDataAck(int messageId, String topic, boolean isSuccess) {
		super.onPublishDataAck(messageId, topic, isSuccess);
		System.out.println("���ͽ��"+isSuccess);
	}
	
	@Override
	public void onPublishDataResult(int messageId, String topic) {
		super.onPublishDataResult(messageId, topic);
		System.out.println("������Ϣ"+topic);
	}
	
	@Override
	public void onReceiveEvent(int messageId, String topic, byte[] data) {
		super.onReceiveEvent(messageId, topic, data);
		String newstring=bytes2hex01(data);
		String subnewstring=newstring.substring(0, newstring.length()-4);
		System.out.println(newstring);
		GlobelData gdata=GlobelData.getInstance();
		gdata.newstring=newstring;
		System.out.println("�յ�����"+newstring);
		//System.out.println("��ȡǰλ"+subnewstring);
		String subendstring=newstring.substring(newstring.length()-4,newstring.length());
		//System.out.println("��ȡ��У����"+subendstring);
		String finalstring=CRC16.checkcode(subnewstring);
		System.out.println("�������ݵõ���У����"+finalstring);
		if(subendstring.equals(finalstring)==true)
		{
			System.out.println("У��ɹ���");
			if(newstring.length()>45)
			{
			Device newdevice=analysisdata(newstring);
			newdevice.DeviceID=topic.substring(topic.length()-15, topic.length());
			System.out.println("�豸ID"+newdevice.DeviceID+"��ѹ��"+newdevice.volt+"�¶�"+newdevice.temperature
				    +"���ӿ���"+newdevice.elecunlock+"���ӹ���"+newdevice.eleclock+"�ֶ�����"+newdevice.handlock);
		}
		}
		else System.out.println("У��ʧ�ܣ�������");
	}
	public static String bytes2hex01(byte[] bytes)
    {
        /**
         * ��һ�������Ľ��ͣ��ǵ�һ��Ҫ����Ϊ1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(16);
    }
	
	public static Device analysisdata(String receivecode)
    {
    	Device device=new Device();
    	//�ɼ���ѹ
    	String volthex=receivecode.substring(38, 42);
    	device.volt=dealwithvolt(volthex);
    	//�ɼ��¶�
    	String temphex=receivecode.substring(42, 46);
    	device.temperature=dealwithtemperature(temphex);
    	//�ɼ�����Ϣ
    	String allforlock=receivecode.substring(37,38);
    	char lockcode=allforlock.charAt(0);
    	String lockbinary=Hex2Binary(lockcode);
    	//�ɼ����ӿ�����Ϣ
    	char elecunlockcode=lockbinary.charAt(3);
    	if(elecunlockcode=='0')device.elecunlock=false;
    	else device.elecunlock=true;
    	//�ɼ����ӹ�����Ϣ
    	char eleclockcode=lockbinary.charAt(2);
    	if(eleclockcode=='0')device.eleclock=false;
    	else device.eleclock=true;
    	//�ɼ��ֶ�������Ϣ
    	char handlockcode=lockbinary.charAt(1);
    	if(handlockcode=='0')device.handlock=false;
    	else device.handlock=true;
    	return device;
    }
    public static float dealwithtemperature(String temphex)
    {
    	float temp=Integer.parseInt(temphex,16);
    	float finaltemp=(float) (temp*0.1-50);
    	return finaltemp;
    }
    public static float dealwithvolt(String volthex)
    {
    	float volt=Integer.parseInt(volthex,16);
    	float finalvolt=(float) (volt*0.01);
    	return finalvolt;
    }
    private static String Hex2Binary(char charAt) {  
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