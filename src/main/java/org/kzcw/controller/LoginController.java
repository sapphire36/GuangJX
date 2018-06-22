package org.kzcw.controller;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.kzcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    //登录管理
	@Autowired
	UserService userservice;
	
    @RequestMapping(value="/dologin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> dologin(ModelMap map,@RequestParam String name,@RequestParam String passwd,HttpServletRequest request){
		//登录验证
    	Map<String,Object> result=new HashMap<String,Object>();
		return result;
    }
    
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String map(){
    	//返回登录页面
        return "/login/login";
    }
}
