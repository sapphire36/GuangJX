package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class IndexController {

	
    @RequestMapping("/index")
    public String index(){
        return "Manage";
    }
    
    @RequestMapping("/getdata")
    @ResponseBody
    public Map<String,Object> data(){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("test","hello world");
		return result;
    }
    
    @RequestMapping("/map")
    public String map(){
        return "Map";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "Login";
    }
}
