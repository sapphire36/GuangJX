package org.kzcw.controller.manage;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.utils.SystemData;
import org.kzcw.model.Breakhistory;
import org.kzcw.model.Lightbox;
import org.kzcw.model.Lockdevice;
import org.kzcw.model.Status;
import org.kzcw.service.BreakhistoryService;
import org.kzcw.service.LightboxService;
import org.kzcw.service.LockdeviceService;
import org.kzcw.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/device")
public class DeviceManager {
	//箱体信息管理
	@Autowired
	LightboxService lservice;
	//设备状态历史记录
	@Autowired
	StatusService staservice;
	//设备锁数据库服务
	@Autowired 
	LockdeviceService lockservice;
	//故障历史表
	@Autowired 
	BreakhistoryService breakservice;
	
	//************************************箱体信息管理**************************
	//************************************箱体信息管理**************************
    @RequestMapping(value="/lightboxlist",method = RequestMethod.GET)
    public String lightboxlist(ModelMap model,HttpServletRequest request){
		//获取箱体信息列表
    	model.addAttribute("list",lservice.list());
		return "/device/lightboxlist";
    }		
		
		
    @RequestMapping(value="/addlightbox",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addlightbox(ModelMap map,HttpServletRequest request){
		//添加箱体信息
        Map<String,String> result=new HashMap<String,String>();
        String name=request.getParameter("NAME"); //获取参数NAME
        String lid=request.getParameter("LOCKID"); //获取参数LOCKID
        String spe=request.getParameter("SPEC"); //获取参数SPEC
        String type=request.getParameter("MADETYPE"); //获取参数MADETYPE
        String loca=request.getParameter("LOCATION"); //获取参数LOCATION
        String people=request.getParameter("PEOPLE"); //获取参数PEOPLE
        try {
            Lightbox ld=new Lightbox();
            ld.setNAME(name);
            ld.setIMEI(lid);
            ld.setSPEC(spe);
            ld.setMADETYPE(type);
            ld.setLOCATION(loca);
            ld.setPEOPLE(people);
            lservice.save(ld);
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/getlightbox",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> getlightbox(ModelMap map,HttpServletRequest request){
		//获取箱体信息
        Map<String,String> result=new HashMap<String,String>();
        String ID=request.getParameter("ID"); //获取参数ID
        try {
            Lightbox ld=lservice.findUniqueByProperty("ID",Long.parseLong(ID));
            if(ld!=null) {
            	result.put("NAME",ld.getNAME());
            	result.put("LOCKID",ld.getIMEI());
            	result.put("SPEC",ld.getSPEC());
            	result.put("MADETYPE",ld.getMADETYPE());
            	result.put("LOCATION",ld.getLOCATION());
            	result.put("PEOPLE",ld.getNAME());          	
            	result.put("data","true");
            	System.out.println("yes");
            } else {
            	System.out.println("not found");
            }
            
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/editlightbox",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editlightbox(ModelMap map,HttpServletRequest request){
		//编辑箱体信息
        Map<String,String> result=new HashMap<String,String>();
        String name=request.getParameter("NAME"); //获取参数NAME
        String lid=request.getParameter("LOCKID"); //获取参数EMEI
        String spe=request.getParameter("SPEC"); //获取参数SPEC
        String type=request.getParameter("MADETYPE"); //获取参数MADETYPE
        String loca=request.getParameter("LOCATION"); //获取参数NAME
        String peo=request.getParameter("PEOPLE"); //获取参数EMEI
        String ID=request.getParameter("ID"); //获取参数ID
        try {
            Lightbox ld=lservice.findUniqueByProperty("ID",Long.parseLong(ID));
            ld.setNAME(name);
            ld.setIMEI(lid);
            ld.setSPEC(spe);
            ld.setMADETYPE(type);
            ld.setLOCATION(loca);
            ld.setPEOPLE(peo);
            lservice.update(ld);
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/deletelightbox",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deletelightbox(ModelMap map,HttpServletRequest request){
		//删除箱体信息
        Map<String,String> result=new HashMap<String,String>();
        String ID=request.getParameter("ID"); //获取参数ID
        try {
            Lightbox ld=lservice.findUniqueByProperty("ID",Long.parseLong(ID));
            lservice.delete(ld);
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }

    
    
    
  //************************************设备状态历史记录**************************
  	//************************************设备状态历史记录**************************
    @RequestMapping(value="/statuslist",method = RequestMethod.GET)
    public String statuslist(ModelMap model,HttpServletRequest request){
		//获取设备状态历史记录列表
    	model.addAttribute("list",staservice.list());
		return "/device/statuslist";
    }
    
    
    
    
    //**********************************设备锁管理*******************************
    //**********************************设备锁管理*******************************
    @RequestMapping(value = "/lockdevicelist", method = RequestMethod.GET)
	public String lockdevicelist(ModelMap map,HttpServletRequest request){
		//获取锁设备列表
		//map.addAttribute("llist",staservice.list());
     	//String sql="select * from t_lightbox";
     	//lockservice.findMapByExecSQL(sql);
     	map.addAttribute("list",lockservice.list());
		return "/device/lockdevicelist";
	}
    
    @RequestMapping(value="/addlockdevice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addlockdevice(ModelMap map,HttpServletRequest request){
		//添加NB-IoT设备
        Map<String,String> result=new HashMap<String,String>();
        String name=request.getParameter("NAME"); //获取参数NAME
        String emei=request.getParameter("EMEI"); //获取参数EMEI
        try {
            Lockdevice ld=new Lockdevice();
            ld.setIMEI(emei);
            ld.setNAME(name);
            lockservice.save(ld);
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/getlockdevice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> getlockdevice(ModelMap map,HttpServletRequest request){
		//获取NB-IoT设备
        Map<String,String> result=new HashMap<String,String>();
        String ID=request.getParameter("ID"); //获取参数ID
        try {
            Lockdevice ld=lockservice.findUniqueByProperty("ID",Long.parseLong(ID));
            if(ld!=null) {
            	result.put("NAME",ld.getNAME());
            	result.put("IMEI",ld.getIMEI());
            	result.put("data","true");
            	System.out.println("yes");
            } else {
            	System.out.println("not found");
            }
            
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/editlockdevice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editlockdevice(ModelMap map,HttpServletRequest request){
		//编辑NB-IoT设备
        Map<String,String> result=new HashMap<String,String>();
        String name=request.getParameter("NAME"); //获取参数NAME
        String emei=request.getParameter("EMEI"); //获取参数EMEI
        String ID=request.getParameter("ID"); //获取参数ID
        try {
            Lockdevice ld=lockservice.findUniqueByProperty("ID",Long.parseLong(ID));
            ld.setIMEI(emei);
            ld.setNAME(name);
            lockservice.update(ld);
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    @RequestMapping(value="/deletelockdevice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deletelockdevice(ModelMap map,HttpServletRequest request){
		//删除NB-IoT设备
        Map<String,String> result=new HashMap<String,String>();
        String ID=request.getParameter("ID"); //获取参数ID
        try {
            Lockdevice ld=lockservice.findUniqueByProperty("ID",Long.parseLong(ID));
            lockservice.delete(ld);
            result.put("data","true"); //执行成功
		} catch (Exception e) {
			// TODO: handle exception
			result.put("data","false");
		}
        return result;
    }
    
    //每个5秒执行一次
	@Scheduled(cron = "0/20 * * * * ? ")
	public void check(){
		//System.out.println("this is form scheduled");
		SystemData systemdata=SystemData.getInstance();
		if(systemdata.statuslist.size()>0) {
			for(Status s:systemdata.statuslist) {
				staservice.save(s);
			}
			systemdata.statuslist.clear();
		}
		if(systemdata.breaklist.size()>0) {
			for(Breakhistory s:systemdata.breaklist) {
				breakservice.save(s);
			}
			systemdata.breaklist.clear();
		}
	}



//************************************故障历史表**************************
	//************************************故障历史表**************************
@RequestMapping(value="/breakhistorylist",method = RequestMethod.GET)
public String breakhistorylist(ModelMap model,HttpServletRequest request){
	//获取设备状态历史记录列表
	model.addAttribute("list",breakservice.list());
	return "/device/breakhistorylist";
}
}
