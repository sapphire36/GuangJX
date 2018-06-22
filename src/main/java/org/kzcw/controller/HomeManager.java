package org.kzcw.controller;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.model.Lightbox;
import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeManager {
    //主页管理
	@Autowired
	LightboxService lservice;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexview(ModelMap map,HttpServletRequest request){
		Lightbox box=new Lightbox();
		box.setLOCATION("tettt");
		box.setLOCKID(1);
		box.setSPEC("tete");
		box.setMADEADDRESS("tetete");
		box.setID(1234);
		lservice.save(box);
		return "/home/index";
	}
}