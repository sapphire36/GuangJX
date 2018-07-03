package org.kzcw.controller.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.Iot.utils.CloseMessage;
import org.kzcw.common.Iot.utils.OpenMessage;
import org.kzcw.common.global.CloseLockList;
import org.kzcw.common.global.OpenLockList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class ServiceInterface {
	
	
	@RequestMapping(value="/openLock",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> openLock(ModelMap map,@RequestParam String UNAME,@RequestParam String EMEI,HttpServletRequest request){
		//开锁申请
		Map<String,Object> result=new HashMap<String,Object>();
		OpenLockList list=OpenLockList.getInstance();
		OpenMessage message=new OpenMessage(UNAME,EMEI);
		//message;
		String emei=getEMEI(EMEI);
		message.USERNAME=UNAME;
		message.time=new Date().toString();
		message.EMEI=emei;
		list.AddItem(message);
		result.put("data","申请成功,请耐心等待审核开锁..");
		return result;
	}
	
	@RequestMapping(value="/closeLock",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> closeLock(ModelMap map,@RequestParam String UNAME,@RequestParam String EMEI,HttpServletRequest request){
		//开锁申请
		Map<String,Object> result=new HashMap<String,Object>();
		CloseLockList list=CloseLockList.getInstance();
		CloseMessage message=new CloseMessage(UNAME,EMEI);
		//message;
		String emei=getEMEI(EMEI);
		message.USERNAME=UNAME;
		message.time=new Date().toString();
		message.EMEI=emei;
		list.AddItem(message);
		result.put("data","关锁成功..");
		return result;
	}
	
	public String getEMEI(String tg) {
		tg=tg.replaceAll(" ","");
		int i=tg.indexOf("I:");
		int j=tg.indexOf("SN");
		return tg.substring(i+2,j);
	}
}