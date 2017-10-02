package com.iot1.sql.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot1.sql.user.dto.UserInfo;
import com.iot1.sql.user.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService us;
	
	@RequestMapping(value="/user/login", method=RequestMethod.GET)
	public String login(HttpSession hs)
	{
		if(hs.getAttribute("user")!=null){
			return "common/main";
		}
		return "user/login";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public @ResponseBody ModelMap login(HttpSession hs, @RequestBody UserInfo user, ModelMap hm)
	{
		UserInfo rUser = us.login(user);
		if(rUser != null)
		{
			hs.setAttribute("user", rUser);
			hm.put("msg", "로그인 성공하셨습니다.");
			hm.put("url", "common/main");
		}
		else
		{
			hm.put("msg", "아이디와 비밀번호를 확인해주세요");
			hm.put("url", "user/login");
		}
		return hm;
	}
	
	@RequestMapping(value="/user/list", method=RequestMethod.POST)
	public @ResponseBody List<UserInfo> getUserList(HttpSession hs,UserInfo user, ModelMap hm)
	{
		return us.selectUserList(user);
	}
	
	@RequestMapping(value="/user/update", method=RequestMethod.POST)
	public @ResponseBody UserInfo updateUser(HttpSession hs,UserInfo user, ModelMap hm)
	{
		return us.updateUser(user);
	}
	
	@RequestMapping(value="/user/insert", method=RequestMethod.POST)
	public @ResponseBody UserInfo saveUserList(HttpSession hs,@RequestBody UserInfo[] user, ModelMap hm)
	{
		return null;
	}
}
