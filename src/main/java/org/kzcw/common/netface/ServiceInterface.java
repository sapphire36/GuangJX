package org.kzcw.common.netface;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ServiceInterface {
	
	
	//@RequestMapping(value="/test",method = RequestMethod.GET)
	@RequestMapping("/test")
	@ResponseBody
	public Map<String,Object> GetCustomerNo(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("test","value1");
		return result;
	}
}