package org.kzcw.controller.manage;

import javax.servlet.http.HttpServletRequest;
import org.kzcw.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/manage/constructor")
public class ConstructionManager {
 //施工方管理
	
	@Autowired
	OrganizationService orgservice;
	
	@RequestMapping(value = "/getview/constructorlist", method = RequestMethod.GET)
	public String onstructlist(ModelMap map,HttpServletRequest request){
		//获取施工方列表
		map.addAttribute("constructorllist",orgservice.list());
		return "constructor/constructorlist";
	}
	
	@RequestMapping(value = "/getview/operahistorylist", method = RequestMethod.GET)
	public String operahistorylist(ModelMap map,HttpServletRequest request){
		//获取施工方历史列表
		return "constructor/operahistorylist";
	}
}
