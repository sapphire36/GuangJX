package org.kzcw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kzcw.model.Lightbox;
import org.kzcw.model.User;
import org.kzcw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	// 登录管理
	@Autowired
	UserService userservice;

	@RequestMapping(value = "/getlogin", method = RequestMethod.GET)
	public String getlogin(String username, String password, Map<String, Object> map) {
		if (username.equals("?") && password.equals("?")) {
			map.put("NAME", username);// 存放在request请求域中
			/**
			 * 类上加上@SessionAttributes({"username"}) 同时也会存放在 session域中
			 * 
			 * @SessionAttributes 除了可以通过属性名指定需要存放到会话中的属性外(使用的是value属性值)
			 *                    还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是types属性值),
			 */
			return "/login/login";
		}
		return "error";
	}
}
