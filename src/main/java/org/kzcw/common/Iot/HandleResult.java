package org.kzcw.common.Iot;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.kzcw.common.Iot.youren.CRC16;
import org.kzcw.common.Iot.youren.ReceiveData;
import org.kzcw.common.Iot.youren.ResultData;
import org.kzcw.common.global.BreakHistoryManager;
import org.kzcw.common.global.SysLogManager;
import org.kzcw.common.global.SystemData;
import org.kzcw.common.global.WaitPublishQueue;
import org.kzcw.model.Breakhistory;
import org.kzcw.model.Status;

public class HandleResult implements Runnable{
    //处理返回结果
	public ReceiveData receivedata;
    SystemData systemdata=SystemData.getInstance();
    SysLogManager systemlog=SysLogManager.getInstance();
    BreakHistoryManager breakhistorymanager=BreakHistoryManager.getInstance();
	
	public HandleResult(ReceiveData receive) {
		// TODO Auto-generated constructor stub
		this.receivedata=receive;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ParsingData(receivedata);//解析
	}
	
	public void ParsingData(ReceiveData data) {
		
		String newstring = bytes2hex01(data.data);
		String subnewstring = newstring.substring(0, newstring.length() - 4);
		String subendstring = newstring.substring(newstring.length() - 4, newstring.length());
		String finalstring = CRC16.checkcode(subnewstring);
		if (subendstring.equals(finalstring) == true) {
			if (newstring.length() > 45) {
				ResultData newdevice = analysisdata(newstring);
				newdevice.DeviceID = data.topic.substring(data.topic.length() - 15, data.topic.length());
				WaitPublishQueue que = WaitPublishQueue.getInstance();
				que.doOperate(newdevice.DeviceID); //执行操作
				
				Map<String,Object> map=checkstatus(newdevice);
		 
				if(!((String)map.get("STATUS")).equals("正常")) {
					Breakhistory breakhistory=new Breakhistory();
					breakhistory.setIEME(newdevice.DeviceID);
					breakhistory.setTYPE((String)map.get("STATUS"));
					breakhistory.setADDTIME(new Date());
					breakhistorymanager.addItem(breakhistory.getIEME(),breakhistory);
				}
		 
				
				Status status=new Status();
				status.setIEME(newdevice.DeviceID);
				if(newdevice.eleclock) //设置电子锁开
					status.setLOCKSTATUS(1);
				else
					status.setLOCKSTATUS(0);
				
				if(newdevice.elecunlock) //设置电子锁关
					status.setUNLOCKSTATUS(1);
				else
					status.setUNLOCKSTATUS(0);
				
				if(newdevice.handlock) //设置电子锁开
					status.setDOORSTATUS(1);
				else
					status.setDOORSTATUS(0);
				
				
				status.setTEMPERATURE(String.valueOf(newdevice.temperature));
				status.setVOLTAGE(String.valueOf(newdevice.volt));
				status.setADDTIME(new Date());
				
				systemdata.statuslist.add(status); //将status添加到缓冲队列
				
				systemlog.addLog("接收到数据" + newdevice.DeviceID, 1);
				
			}
		} else {
			systemlog.addLog("CRC校验失败!!", 1);
		}
	}
	
	
	public Map<String,Object> checkstatus(ResultData data) {
		//检查结果
		Map<String,Object> ret=new HashMap<String,Object>();
		//检查故障情况
		String result=":";
		int lock=0;
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
        ret.put("STATUS", result);
        //检查开锁状态
		if(data.eleclock) {
			lock=1;
		}
		
		if(data.elecunlock) {
			lock=0;
		}
		ret.put("LOCK", lock);
        return ret;
	}
	
	public String getIMEIbyTopic(String topic) {
		//获取IMEI编号
		String ret = "";
		for (int i = 0; i < topic.length(); i++) {
			if ((topic.charAt(i) > '0') && (topic.charAt(i) < '9')) {
				ret += topic.charAt(i);
			}
		}
		return ret;
	}
	
	public static String bytes2hex01(byte[] bytes) {
		BigInteger bigInteger = new BigInteger(1, bytes);
		return bigInteger.toString(16);
	}

	public ResultData analysisdata(String receivecode) {
		//分析数据
		ResultData device = new ResultData();
		String volthex = receivecode.substring(38, 42);
		device.volt = getvolt(volthex);
		String temphex = receivecode.substring(42, 46);
		device.temperature = gettemperature(temphex);
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

	public float gettemperature(String temphex) {
		//获取温度
		float temp = Integer.parseInt(temphex, 16);
		float finaltemp = (float) (temp * 0.1 - 50);
		return finaltemp;
	}

	public float getvolt(String volthex) {
		//获取电压
		float volt = Integer.parseInt(volthex, 16);
		float finalvolt = (float) (volt * 0.01);
		return finalvolt;
	}

	public String Hex2Binary(char charAt) {
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
