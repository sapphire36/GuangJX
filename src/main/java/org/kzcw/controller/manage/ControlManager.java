package org.kzcw.controller.manage;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.Iot.WaitQueue;
import org.kzcw.common.Iot.utils.CloseLockList;
import org.kzcw.common.Iot.utils.CloseMessage;
import org.kzcw.common.Iot.utils.GradeList;
import org.kzcw.common.Iot.utils.GradeMessge;
import org.kzcw.common.Iot.utils.OpenLockList;
import org.kzcw.common.Iot.utils.OpenMessage;
import org.kzcw.common.Iot.youren.OperaType;
 
import org.kzcw.model.Operatehistory;
import org.kzcw.service.LightboxService;
import org.kzcw.service.OperatehistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/control")
public class ControlManager {
	//开锁,关锁队列管理
	
	@Autowired
	LightboxService lservice;
	
	@Autowired
	OperatehistoryService operahistory;
	
	@RequestMapping(value = "/getview/getcontrolview", method = RequestMethod.GET)
    public String getcontrolview(ModelMap model,HttpServletRequest request){
    	//控制界面
		return "/control/control";
    }
	
	@RequestMapping(value = "/gettodolist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> gettodolist(ModelMap model,HttpServletRequest request){
    	//获取有待执行队列
    	Map<String,Object> result=new HashMap<String,Object>();
    	WaitQueue list=WaitQueue.getInstance();//获取开锁队列
    	if(list.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
    		Map<String,OperaType> sets=list.getMapEntrySet();
	        for(Map.Entry<String, OperaType> entry: sets.entrySet())
	        {
	    		stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#open\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(entry.getKey());
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			if(entry.getValue().type==1)
    				stringBuffer.append("正在执行开锁操作");
    			else
    				stringBuffer.append("正在执行关锁操作");
    			stringBuffer.append("</a>");
	        }
    	
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
     		list.IsFlush=false;//设置不刷新
    	}else {
    		//不执行
    		result.put("data","");
    		result.put("IsFlush","false");
    	}
		return result;
    }
	
	
	@RequestMapping(value = "/getopenlist", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> getopenlist(ModelMap model,HttpServletRequest request){
    	//获取开锁列表
    	Map<String,Object> result=new HashMap<String,Object>();
    	OpenLockList list=OpenLockList.getInstance();//获取开锁队列
    	if(list.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
    		int index=0;
            for (OpenMessage x : list.list) { 
    			stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#open\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(x.USERNAME+x.time);
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			if(x.IsReady)
    				stringBuffer.append("正在打开:"+x.EMEI);
    			else
    				stringBuffer.append("等待开锁:"+x.EMEI);
    			//stringBuffer.append("<input type=\"hidden\" value=\"");
    			//stringBuffer.append(index);
    			//stringBuffer.append("\">");
    			stringBuffer.append("</a>");
    			index++;
            } 
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
     		list.IsFlush=false;//设置不刷新
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
    	CloseLockList list=CloseLockList.getInstance();//获取开锁队列
    	if(list.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
    		int index=0;
            for (CloseMessage x : list.list) { 
    			stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#close\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(x.USERNAME+x.time);
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			if(x.IsReady)
    				stringBuffer.append("正在关闭:"+x.EMEI);
    			else
    				stringBuffer.append("等待关锁:"+x.EMEI);
    			stringBuffer.append("</a>");
    			index++;
            } 
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
     		list.IsFlush=false;//设置不刷新
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
    	GradeList list=GradeList.getInstance();//获取开锁队列
    	if(list.IsFlush) {
    		//执行刷新操作
    		StringBuffer stringBuffer = new StringBuffer();
    		int index=0;
            for (GradeMessge x : list.list) { 
    			stringBuffer.append("<a href=\"#\" class=\"list-group-item\" data-toggle=\"modal\" data-backdrop=\"static\" data-target=\"#grade\"> ");
    			stringBuffer.append("<span class=\"badge\">");	
    			stringBuffer.append(x.USERNAME+x.time);
    			stringBuffer.append("</span><i class=\"fa fa-fw fa-comment\"></i>");
    			stringBuffer.append(x.EMEI);
 
    			stringBuffer.append("</a>");
    			index++;
            } 
    		result.put("data", stringBuffer.toString());
    		result.put("IsFlush","true");
    		list.IsFlush=false;//设置不刷新
    	}else {
    		//不执行
    		result.put("data","");
    		result.put("IsFlush","false");
    	}
		return result;
    }
	
	@RequestMapping(value = "/doopenlock", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> doopenlock(ModelMap model,@RequestParam int ID,HttpServletRequest request){
    	//执行开锁操作
    	Map<String,Object> result=new HashMap<String,Object>();
    	OpenLockList olist=OpenLockList.getInstance();
    	OpenMessage message=olist.getByIndex(ID);
    	if(message!=null) {
    		WaitQueue queque=WaitQueue.getInstance();
    		queque.AddItem(new OperaType(message.EMEI,1));//加入开锁队列
    		olist.DelItem(ID);
    		result.put("data","true");
    	}
    	else {
    		result.put("data","false");
    	}
		return result;
    }
	
	@RequestMapping(value = "/docloselock", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> docloselock(ModelMap model,@RequestParam int ID,HttpServletRequest request){
		//执行关锁操作
    	Map<String,Object> result=new HashMap<String,Object>();
    	CloseLockList clist=CloseLockList.getInstance();
    	CloseMessage message=clist.DelItem(ID);
    	if(message!=null) {
    		WaitQueue queque=WaitQueue.getInstance();
    		queque.AddItem(new OperaType(message.EMEI,0));//加入关锁队列
    		clist.DelItem(ID);
    		result.put("data","true");
    	}
    	else {
    		result.put("data","false");
    	}
		return result;
    }
	
	@RequestMapping(value = "/dograde", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> dograde(ModelMap model,@RequestParam int ID,HttpServletRequest request){
		//执行评分操作
    	Map<String,Object> result=new HashMap<String,Object>();
    	GradeList list=GradeList.getInstance();
    	GradeMessge message=list.DelItem(ID);
    	if(message!=null) {
    		result.put("data","false");
    		return result;
    	}
    	try {
          	Operatehistory history=new Operatehistory();
        	history.setBOXID(1);
        	history.setORGANIZATIONID(105);
         	history.setSCORE(7);
        	operahistory.save(history);
        	result.put("data","true");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
    	return result;
    }
	
	@RequestMapping(value = "/dorefresh", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> dorefresh(ModelMap model,HttpServletRequest request){
		//离开页面时更新IsFlush
    	Map<String,Object> result=new HashMap<String,Object>();
    	OpenLockList openlist=OpenLockList.getInstance();
    	CloseLockList closeque=CloseLockList.getInstance();
    	GradeList gradeque=GradeList.getInstance();
    	openlist.IsFlush=true;
    	closeque.IsFlush=true;
    	gradeque.IsFlush=true;
    	result.put("data","true");
		return result;
    }
}
