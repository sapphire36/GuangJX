package org.kzcw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.model.Lightbox;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeManager {
    //主页管理
	@Autowired
	LightboxService lservice;
	
	@RequestMapping(value = "/getboxbyid", method = RequestMethod.GET)
	public String uaccrualform(ModelMap map,@RequestParam int ID,HttpServletRequest request)
			throws Exception {
		
		Lightbox bLightbox=null;
	  	List<Lightbox> list=lservice.list();
    	for(int i=0;i<list.size();i++) {
    		if(list.get(i).ID==ID) {
    			bLightbox=list.get(i);
    		}
    	}
	   
	    if(bLightbox==null) {
	    	map.addAttribute("addrr", "not found");
	    	map.addAttribute("loc", "not found");
	    }else {
	    	map.addAttribute("addrr", bLightbox.getMADEADDRESS());
	    	map.addAttribute("loc", bLightbox.getLOCATION());
	    }
	    map.addAttribute("datalist", lservice.list());
		return "XT";
	}
}
