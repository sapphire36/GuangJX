package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kzcw.model.Lightbox;
import org.kzcw.service.LightboxService;
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
    
    @RequestMapping(value="/lightboxlist",method = RequestMethod.GET)
    public String lightboxinfo(ModelMap model,HttpServletRequest request){
		//获取设备列表
     	model.addAttribute("llist",lservice.list());
        return "/device/lightboxlist";
    }
    
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
    
    @RequestMapping(value="/dodelete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> dodelete(ModelMap model,@RequestParam int ID,HttpServletRequest request){
		//删除设备
    	Map<String,String> result=new HashMap<String,String>();
     	Lightbox lightbox=lservice.findById(ID);
        return result;
    }
}
