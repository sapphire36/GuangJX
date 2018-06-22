package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/control")
public class ControlManager {
	//开锁,关锁队列管理

	@Autowired
	LightboxService lservice;
	
	@RequestMapping(value = "/getopenlist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getopenlist(ModelMap model,HttpServletRequest request){
    	//获取开锁列表
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
    
	@RequestMapping(value = "/getcloselist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getcloselist(ModelMap model,HttpServletRequest request){
    	//获取关锁列表
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
	
	@RequestMapping(value = "/getgradelist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getgradelist(ModelMap model,HttpServletRequest request){
    	//获取等待评分列表
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
	
	@RequestMapping(value = "/doopenlock", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> doopenlock(ModelMap model,HttpServletRequest request){
    	//执行开锁操作
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
	
	@RequestMapping(value = "/docloselock", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> docloselock(ModelMap model,HttpServletRequest request){
		//执行关锁操作
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
	
	@RequestMapping(value = "/dograde", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> dograde(ModelMap model,HttpServletRequest request){
		//执行评分操作
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
}
