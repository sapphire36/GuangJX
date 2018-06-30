package org.kzcw.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.core.Globals;
import org.kzcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	// 登录管理
	@Autowired
	UserService userservice;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map,HttpServletRequest request) {
 
			return "/login/login";
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> dologin(ModelMap map,HttpServletRequest request) {
		 //判断执行登录
		
		 Map<String,String> ret=new HashMap<String,String>();
		 String name=request.getParameter("NAME"); //获取用户名
	     String passwd=request.getParameter("PASSWD");
	     if(name.equals("test")&&passwd.equals("123")) {
	    	 //设置登录session标示
	    	 request.getSession().setAttribute(Globals.OnlineUserManageFlag, "true");
	    	 ret.put("data","true");
	     }else {
	    	 ret.put("data","false");
	     }
	     return ret;
	     
	}
}
