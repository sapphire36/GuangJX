package org.kzcw.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/monitor")
public class MonitorManager {
       //监控中心
	
	@RequestMapping(value = "/getmap", method = RequestMethod.GET)
	public String getmap(ModelMap map,HttpServletRequest request){
		//返回地图
		return "/monitor/map";
	}
}
