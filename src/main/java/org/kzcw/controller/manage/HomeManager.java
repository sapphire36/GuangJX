package org.kzcw.controller.manage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.Iot.youren.YouRenManger;
import org.kzcw.common.tools.OpenLockList;
import org.kzcw.common.tools.OpenMessage;
import org.kzcw.model.Module;
import org.kzcw.service.LightboxService;
import org.kzcw.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/home")
public class HomeManager {
    //主页管理
	@Autowired
	LightboxService lservice;
	
	@Autowired
	ModuleService mservice;
	
	
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
	
	@RequestMapping(value = "/additem", method = RequestMethod.GET)
	public String additem(ModelMap map,HttpServletRequest request){
		//NAME,int CODE,int ISLEAF,String URL,String IMAGE,int PARRENTCODE
		List<Module> list=new ArrayList<Module>();
		list.add(new Module("首页",1,0,"/home/manage/index","fa-qrcode",1)); 
		
		list.add(new Module("监控中心",2,0,"/home/manage/index","fa-qrcode",2)); 
		list.add(new Module("地图",3,1,"/home/manage/index","fa-qrcode",2)); 
		list.add(new Module("数据",4,1,"/home/manage/index","fa-qrcode",2)); 
		
		list.add(new Module("设备管理",5,0,"/home/manage/index","fa-qrcode",5)); 
		list.add(new Module("光交箱信息管理",6,1,"/home/manage/index","fa-qrcode",5)); 
		list.add(new Module("设备状态历史记录",7,1,"/home/manage/index","fa-qrcode",5)); 
		list.add(new Module("NB-IoT锁管理",8,1,"/home/manage/index","fa-qrcode",5)); 
		list.add(new Module("故障历史表",9,1,"/home/manage/index","fa-qrcode",5)); 
		
		list.add(new Module("施工方管理",10,0,"/home/manage/index","fa-qrcode",10)); 
		list.add(new Module("施工方信息管理",11,1,"/home/manage/index","fa-qrcode",10)); 
		list.add(new Module("施工方角色管理",12,1,"/home/manage/index","fa-qrcode",10)); 
		list.add(new Module("施工操作历史",13,1,"/home/manage/index","fa-qrcode",10)); 
		
		list.add(new Module("光交箱改造方管理",14,0,"/home/manage/index","fa-qrcode",14)); 
		list.add(new Module("交箱改造方信息管理",15,1,"/home/manage/index","fa-qrcode",14)); 
		list.add(new Module("光交箱改造方角色管理",16,1,"/home/manage/index","fa-qrcode",14));
		
		list.add(new Module("系统设置",17,0,"/home/manage/index","fa-qrcode",17)); 
		list.add(new Module("系统参数设置",18,1,"/home/manage/index","fa-qrcode",17)); 
		
		list.add(new Module("修改密码",19,0,"/home/manage/index","fa-qrcode",19)); 
		//mservice.save(t);
		
		for(Module m:list) {
			mservice.save(m);
		}
		return "/home/index";
	}
}
