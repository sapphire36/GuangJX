package org.kzcw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.model.Lightbox;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control")
public class ControlManager {
	//开锁,关锁队列管理

	@Autowired
	LightboxService lservice;
	
    @RequestMapping("/manage")
    public String index(ModelMap model,HttpServletRequest request){
    	
    	List<Lightbox> list=lservice.list();
    	String str="";
    	for(int i=0;i<list.size();i++) {
    		str=str+list.get(i).getLOCKID()+" ";
    	}
    	model.addAttribute("info",str);
    	model.addAttribute("info1","this is a test");
        return "Manage";
    }
    
    @RequestMapping("/list")
    public String get(ModelMap model,HttpServletRequest request){
    	
    	model.addAttribute("info",lservice.list().size());
    	
        return "Manage";
    }
}
