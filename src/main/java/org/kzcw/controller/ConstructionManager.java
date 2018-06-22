package org.kzcw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/constructor")
public class ConstructionManager {
 //施工方管理
	
	@RequestMapping(value = "/constructorlist", method = RequestMethod.GET)
	public String onstructlist(ModelMap map,HttpServletRequest request){
		//获取施工方列表
		return "constructor/constructorlist";
	}
	
	@RequestMapping(value = "/operahistorylist", method = RequestMethod.GET)
	public String operahistorylist(ModelMap map,HttpServletRequest request){
		//获取施工方列表
		return "constructor/operahistorylist";
	}
}
