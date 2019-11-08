package com.controller.developer;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Dev_user;
import com.service.dev_user.Dev_user_Service;

@Controller
@RequestMapping("/devuser")
public class Dev_userController {
	@Resource
	private Dev_user_Service userService;
	
	@RequestMapping("/dologin")
	public String login(@RequestParam(value="devCode",required=false)String devCode,@RequestParam(value="devPassword",required=false)String devPassword, HttpSession session,Model model){
		Dev_user user = userService.login(devCode);
		if(user !=null){
			if(devPassword.equals(user.getDevPassword())){
				session.setAttribute("devUserSession", user);
				return "developer/main";
			}
			model.addAttribute("error","密码不正确！");
			return "devlogin";
		}
		model.addAttribute("error","用户不存在！");
		return "devlogin";
	}
}
