package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.service.Imp.LightboxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    //登录管理
	@Autowired
	LightboxServiceImpl lservice;
	
	
    @RequestMapping("/index")
    public String index(){
        return "Manage";
    }
    
    @RequestMapping("/getdata")
    @ResponseBody
    public Map<String,Object> data(){
		Map<String,Object> result=new HashMap<String,Object>();
		
		result.put("test",lservice.list());
		return result;
    }
    
    @RequestMapping("/map")
    public String map(){
        return "Map";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "Login";
    }
    
	@RequestMapping(value = "/addTest", method = RequestMethod.POST)
	public String uaccrualform(ModelMap map,@RequestParam String FName,@RequestParam int FItemID,@RequestParam String starttime,@RequestParam String endtime,HttpServletRequest request)
			throws Exception {
		map.put("FName", FName);
		map.put("FNumber", FItemID);
		map.put("start", starttime);
		map.put("end", endtime);
		return "/manage/unit/uaccrualform";
	}
}
