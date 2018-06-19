package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.Iot.youren.YourenManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

	
	
    @RequestMapping("/index")
    public String index(){
        return "demo";
    }
    
	@RequestMapping("/openlock")
	@ResponseBody
	public Map<String,Object> GetCustomerNo(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String,Object>();
	       new Thread("线程1"){
	            @Override
	            public void run(){
	        		YourenManager manager=new YourenManager();
	        		manager.OpenLock();
	            }
	        }.start();
		result.put("test","ok");
		return result;
	}
}

