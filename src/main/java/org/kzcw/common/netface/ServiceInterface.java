package org.kzcw.common.netface;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.tools.CloseLockList;
import org.kzcw.common.tools.CloseMessage;
import org.kzcw.common.tools.OpenLockList;
import org.kzcw.common.tools.OpenMessage;
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
		message.USERNAME=UNAME;
		message.time=new Date().toString();
		message.EMEI=EMEI;
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
		message.USERNAME=UNAME;
		message.time=new Date().toString();
		message.EMEI=EMEI;
		list.AddItem(message);
		result.put("data","关锁成功..");
		return result;
	}
}