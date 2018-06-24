package org.kzcw.controller;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.tools.ControlMessage;
import org.kzcw.common.tools.OpenLockQueue;
import org.kzcw.model.Lightbox;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeManager {
    //主页管理
	@Autowired
	LightboxService lservice;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexview(ModelMap map,HttpServletRequest request){
		OpenLockQueue queue=OpenLockQueue.getInstance();
		ControlMessage message=new ControlMessage();
		message.Location="ceshi : q234";
		message.time="1 s";
		queue.InQueue(message);
		return "/home/index";
	}
	
	
}
