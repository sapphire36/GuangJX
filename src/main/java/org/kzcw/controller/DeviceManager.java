package org.kzcw.controller;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kzcw.model.Lightbox;
import org.kzcw.model.Lockdevice;
import org.kzcw.service.LightboxService;
import org.kzcw.service.LockdeviceService;
import org.kzcw.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/device")
public class DeviceManager {
	//箱体信息管理
	@Autowired
	LightboxService lservice;
	//设备锁数据库服务
	@Autowired 
	LockdeviceService lockservice;
	//光交箱状态管理
	@Autowired
	StatusService staservice;
	
	//************************************箱体信息管理**************************
	//************************************箱体信息管理**************************
    @RequestMapping(value="/getlightbox",method = RequestMethod.GET)
    @ResponseBody
    public Lightbox getlightbox(ModelMap model,@RequestParam int ID,HttpServletRequest request){
		//获取设备
     	Lightbox lightbox=lservice.findById(ID);
        return lightbox;
    }
    
    @RequestMapping(value="/doupdate",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> doupdate(ModelMap model,@RequestParam int ID,HttpServletRequest request){
		//更新设备
    	Map<String,String> result=new HashMap<String,String>();
     	Lightbox lightbox=lservice.findById(ID);
        return result;
    }
    
    @RequestMapping(value="/doadd",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> doadd(ModelMap model,HttpServletRequest request){
		//新增设备
    	Map<String,String> result=new HashMap<String,String>();
        return result;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
	public String deleteview(ModelMap map,@RequestParam int ID,HttpServletRequest request){
    	//删除设备
		Lightbox box=lservice.findById(ID);
		if(box!=null)
		  lservice.delete(box);
		return "/device/lightboxlist";
	}
    
    @RequestMapping(value = "/statuslist", method = RequestMethod.GET)
	public String onstructlist(ModelMap map,HttpServletRequest request){
		//获取光交箱状态方列表
		map.addAttribute("llist",staservice.list());
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
     	map.addAttribute("llist",lockservice.list());
		return "/device/lockdevicelist";
	}
    
    @RequestMapping(value="/getlockdevice",method = RequestMethod.POST)
    @ResponseBody
    public Lightbox getlockdevice(ModelMap map,@RequestParam int ID,HttpServletRequest request){
		//获取设备
     	Lightbox lightbox=lservice.findById(ID);

        return lightbox;
    }
    
    @RequestMapping(value="/addlockdevice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addlockdevice(ModelMap map,HttpServletRequest request){
		//获取设备
        Map<String,String> result=new HashMap<String,String>();
        String name=request.getParameter("NAME");
        System.out.println(name);
        Lockdevice ld=new Lockdevice();
        return result;
    }
}
