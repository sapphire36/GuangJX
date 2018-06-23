package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.tools.CloseLockQueue;
import org.kzcw.common.tools.ControlMessage;
import org.kzcw.common.tools.GradeQueue;
import org.kzcw.common.tools.OpenLockQueue;
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
	
	@RequestMapping(value = "/getcontrolview", method = RequestMethod.GET)
    public String getcontrolview(ModelMap model,HttpServletRequest request){
    	//控制界面
		return "/control/control";
    }
	
	@RequestMapping(value = "/getopenlist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getopenlist(ModelMap model,HttpServletRequest request){
    	//获取开锁列表
    	Map<String,Object> result=new HashMap<String,Object>();
    	OpenLockQueue queue=OpenLockQueue.getInstance();//获取开锁队列
    	if(queue.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
            for (ControlMessage x : queue.queue) { 
    			stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#open\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(x.time);
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			stringBuffer.append(x.Location);
    			stringBuffer.append("</a>");
    			stringBuffer.append("<input id=\"");
    			stringBuffer.append("ttt");
    			stringBuffer.append("\" type=\"hidden\" value=\"");
    			stringBuffer.append("mmm");
    			stringBuffer.append("\">");
    			
            } 
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
     		queue.IsFlush=false;//设置不刷新
    	}else {
    		//不执行
    		result.put("data","");
    		result.put("IsFlush","false");
    	}
		return result;
    }
    
	@RequestMapping(value = "/getcloselist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getcloselist(ModelMap model,HttpServletRequest request){
    	//获取关锁列表
    	Map<String,Object> result=new HashMap<String,Object>();
    	CloseLockQueue queue=CloseLockQueue.getInstance();//获取开锁队列
    	if(queue.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
            for (ControlMessage x : queue.queue) { 
    			stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#open\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(x.time);
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			stringBuffer.append(x.Location);
    			stringBuffer.append("</a>");
            } 
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
     		queue.IsFlush=false;//设置不刷新
    	}else {
    		//不执行
    		result.put("data","");
    		result.put("IsFlush","false");
    	}
		return result;
    }
	
	@RequestMapping(value = "/getgradelist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getgradelist(ModelMap model,HttpServletRequest request){
    	//获取等待评分列表
    	Map<String,Object> result=new HashMap<String,Object>();
    	GradeQueue queue=GradeQueue.getInstance();//获取开锁队列
    	if(queue.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
            for (ControlMessage x : queue.queue) { 
    			stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#open\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(x.time);
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			stringBuffer.append(x.Location);
    			stringBuffer.append("</a>");
            } 
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
    		queue.IsFlush=false;//设置不刷新
    	}else {
    		//不执行
    		result.put("data","");
    		result.put("IsFlush","false");
    	}
		return result;
    }
	
	@RequestMapping(value = "/doopenlock", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> doopenlock(ModelMap model,HttpServletRequest request){
    	//执行开锁操作
    	Map<String,Object> result=new HashMap<String,Object>();
    	//System.out.println(request.getParameter("test"));
    	OpenLockQueue queue=OpenLockQueue.getInstance();
    	queue.OutQueue();
    	result.put("data","true");
		return result;
    }
	
	@RequestMapping(value = "/docloselock", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> docloselock(ModelMap model,HttpServletRequest request){
		//执行关锁操作
    	Map<String,Object> result=new HashMap<String,Object>();
    	CloseLockQueue queue=CloseLockQueue.getInstance();
    	queue.OutQueue();
    	result.put("data","true");
		return result;
    }
	
	@RequestMapping(value = "/dograde", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> dograde(ModelMap model,HttpServletRequest request){
		//执行评分操作
    	Map<String,Object> result=new HashMap<String,Object>();
    	GradeQueue queue=GradeQueue.getInstance();
    	queue.OutQueue();
    	result.put("data","true");
		return result;
    }
	
	@RequestMapping(value = "/dorefresh", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> dorefresh(ModelMap model,HttpServletRequest request){
		//离开页面时更新IsFlush
    	Map<String,Object> result=new HashMap<String,Object>();
    	OpenLockQueue openqueue=OpenLockQueue.getInstance();
    	CloseLockQueue closeque=CloseLockQueue.getInstance();
    	GradeQueue gradeque=GradeQueue.getInstance();
    	openqueue.IsFlush=true;
    	closeque.IsFlush=true;
    	gradeque.IsFlush=true;
    	result.put("data","true");
		return result;
    }
}
