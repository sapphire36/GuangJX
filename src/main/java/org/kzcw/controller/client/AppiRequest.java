package org.kzcw.controller.client;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.kzcw.common.tools.HttpTookit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/app")
//启用登陆验证拦截，保证数据保密性
public class AppiRequest {
	
	
	@RequestMapping("/getdata")
	@ResponseBody
	public Map<String,Object> SaveDraw(HttpServletRequest request){
		String str=HttpTookit.doGet("http://127.0.0.1:8080/GuangJX/client/test","", "UTF-8", false).toString();
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("test",str);
		return result;
	}
}