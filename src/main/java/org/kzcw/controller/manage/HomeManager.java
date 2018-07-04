package org.kzcw.controller.manage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.Iot.utils.OpenMessage;
import org.kzcw.common.global.BreakHistoryManager;
import org.kzcw.common.global.OpenLockList;
import org.kzcw.model.Module;
import org.kzcw.service.LightboxService;
import org.kzcw.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/home")
public class HomeManager {
    //主页管理
	@Autowired
	LightboxService lservice;
	
	@Autowired
	ModuleService mservice;
	
	
	@RequestMapping(value = "/getview/index", method = RequestMethod.GET)
	public String indexview(ModelMap map,HttpServletRequest request){
		return "/home/index";
	}
	
    @RequestMapping(value="/getwainning",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> getwainning(ModelMap map,HttpServletRequest request){
		//获取报警数据
        Map<String,String> result=new HashMap<String,String>();
        int len=10;

        try {
            BreakHistoryManager breakhistorymanager=BreakHistoryManager.getInstance();
            if(breakhistorymanager.IsFlush) {
            	//获取当前报警
            	result.put("IsFlush","true"); //执行成功
              	result.put("content",breakhistorymanager.getCurrentBreakMessage()); //执行成功
              	//breakhistorymanager.IsFlush=false;
              	len--;
              	if(len<0) {
              		breakhistorymanager.IsFlush=false;
              	}
            }else {
            	result.put("IsFlush","false"); //执行成功
            }
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
	
	@RequestMapping(value = "/getview/add", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/additem", method = RequestMethod.GET)
	public String additem(ModelMap map,HttpServletRequest request){
		//NAME,int CODE,int ISLEAF,String URL,String IMAGE,int PARRENTCODE
		List<Module> list=new ArrayList<Module>();
		list.add(new Module("首页",1,0,"/GuangJX/manage/home/getview/index","fa-qrcode",0)); 
		
		list.add(new Module("监控中心",2,0,"","fa-qrcode",2)); 
		list.add(new Module("地图",3,1,"/GuangJX/manage/monitor/getview/getmap","",2)); 
		list.add(new Module("数据",4,1,"/GuangJX/manage/control/getview/getcontrolview","",2)); 
		
		list.add(new Module("设备管理",5,0,"","fa-qrcode",5)); 
		list.add(new Module("光交箱信息管理",6,1,"/GuangJX/manage/device/getview/lightboxlist","",5)); 
		list.add(new Module("光交箱状态管理",7,1,"/GuangJX/manage/device/getview/statuslist","",5)); 
		list.add(new Module("NB-IoT锁管理",8,1,"/GuangJX/manage/device/getview/lockdevicelist","",5)); 
		list.add(new Module("故障历史表",9,1,"/GuangJX/manage/device/getview/breakhistorylist","",5)); 
		
		list.add(new Module("施工方管理",10,0,"","fa-qrcode",10)); 
		list.add(new Module("施工方信息管理",11,1,"/GuangJX/manage/constructor/getview/constructorlist","",10)); 
		list.add(new Module("施工方角色管理",12,1,"#","",10)); 
		list.add(new Module("施工操作历史",13,1,"/GuangJX/manage/constructor/getview/operahistorylist","",10)); 
		
		list.add(new Module("光交箱改造方管理",14,0,"#","fa-qrcode",14)); 
		list.add(new Module("交箱改造方信息管理",15,1,"#","",14)); 
		list.add(new Module("光交箱改造方角色管理",16,1,"#","",14));
		
		list.add(new Module("系统设置",17,0,"","fa-qrcode",17)); 
		list.add(new Module("系统参数设置",18,1,"/GuangJX/manage/system/getview/setting","",17)); 
		list.add(new Module("系统日志",20,1,"/GuangJX/manage/system/getview/journallist","",17)); 
		
		list.add(new Module("修改密码",19,0,"#","fa-qrcode",0)); 
		//mservice.save(t);
		mservice.deleteAllItem();//清空表
		for(Module m:list) {
			mservice.save(m);
		}
		return "/home/index";
	}
}