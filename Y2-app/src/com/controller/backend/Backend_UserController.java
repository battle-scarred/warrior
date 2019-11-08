package com.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.App_category;
import com.entity.App_info;
import com.entity.Backend_user;
import com.entity.Data_dictionary;
import com.entity.Dev_user;
import com.entity.PageSupport;
import com.service.backend_user.Backend_user_Service;

@Controller
@RequestMapping("/user")
public class Backend_UserController {
	@Resource
	private Backend_user_Service backend_user_Service;
	
	
	@RequestMapping("/dologin")
	public String login(@RequestParam(value="userCode",required=false)String devCode,@RequestParam(value="userPassword",required=false)String devPassword, HttpSession session,Model model){
		Backend_user user = backend_user_Service.login(devCode);
		if(user !=null){
			if(devPassword.equals(user.getUserPassword())){
				session.setAttribute("userSession", user);
				return "backend/main";
			}
			model.addAttribute("error","密码不正确！");
			return "devlogin";
		}
		model.addAttribute("error","用户不存在！");
		return "devlogin";
	}
	
	
}
