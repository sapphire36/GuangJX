package org.kzcw.controller;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.service.LightboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device")
public class DeviceManager {
	//箱体信息管理
	@Autowired
	LightboxService lservice;
	
    @RequestMapping("/cxtinfo")
    public String index(ModelMap model,HttpServletRequest request){
    	    	    	
    	//List<Lightbox> list=lservice.list();
     	model.addAttribute("llist",lservice.list());
    	/*String spec="";
    	for(int i=0;i<list.size();i++) {
    		spec=spec+list.get(i).getSPEC()+" ";
    	}
    	String madeaddress="";
    	for(int i=0;i<list.size();i++) {
    		madeaddress=madeaddress+list.get(i).getMADEADDRESS()+" ";
    	}
    	String location="";
    	for(int i=0;i<list.size();i++) {
    		location=location+list.get(i).getLOCATION()+" ";
    	}
    	String lockid="";
    	for(int i=0;i<list.size();i++) {
    		lockid=lockid+list.get(i).getLOCKID()+" ";
    	}
    	model.addAttribute("spec",spec);
    	model.addAttribute("madeaddress",madeaddress);
    	model.addAttribute("location",location);
    	model.addAttribute("lockid",lockid);
    	*/
        return "/device/Cxtinfo";
    }
}
