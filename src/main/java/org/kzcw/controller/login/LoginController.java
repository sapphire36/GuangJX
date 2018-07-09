package org.kzcw.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.global.Globals;
import org.kzcw.common.global.SystemData;
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
		 String name=request.getParameter("USERNAME"); //获取用户名
	     String passwd=request.getParameter("PASSWD");
	     String usertype=request.getParameter("USERTYPE");
	     int flag=userservice.doLogin(name, passwd,Long.parseLong(usertype));
	     switch (flag) {
			case 1:{
		    	 //设置登录session标示
				 SystemData systemdata=SystemData.getInstance();
				 systemdata.loginname=name; //设置当前登录用户
				 systemdata.status=usertype;
		    	 request.getSession().setAttribute(Globals.OnlineUserManageFlag,"true");
		    	 request.getSession().setAttribute(Globals.USERNAME, name);//设置用户名
		    	 request.getSession().setAttribute(Globals.USERTYPE,usertype);//设置用户类型
		    	 ret.put("data","1");
			    }
				break;
			case 2:{
		    	 //设置登录session标示
		    	 ret.put("data","2");
			    }
				break;
			default:
				ret.put("data","0");
				break;
		}
	    return ret;
	}
}
