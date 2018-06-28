package org.kzcw.controller;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.Iot.youren.YouRenManger;
import org.kzcw.common.tools.OpenLockList;
import org.kzcw.common.tools.OpenMessage;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeManager {
    //主页管理
	@Autowired
	LightboxService lservice;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexview(ModelMap map,HttpServletRequest request){
		return "/home/index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map,HttpServletRequest request){
		OpenLockList list=OpenLockList.getInstance();
		OpenMessage message=new OpenMessage("test","356566077983401");
		//message;
		message.USERNAME="test";
		message.time=new Date().toString();
		message.EMEI="356566077983401";
		list.AddItem(message);
		return "/home/index";
	}
}
