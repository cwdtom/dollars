package com.dollars.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dollars.dao.UserMapper;
import com.dollars.entity.User;
import com.dollars.mybatis.MyBatisUtil;
import com.dollars.util.Md5Util;

@Controller
public class LoginController {
	
	@RequestMapping("/login.do")
	public String login(String username,String password,Model model,HttpServletResponse resp) throws IOException{
		User user = new User();
		
		if(username == null || password == null){
			return "index";
		}
		user.setUserName(username);
		user.setPassWord(Md5Util.md5(password));
		
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		user = mapper.selectAllByName(user.getUserName());
		session.close();
		
		if(user == null || user.getPassWord().equals(password)){
			model.addAttribute("error", "USERNAME OR PASSWORD INCORRECT");
			return "index";
		}
		
		Cookie cookie=new Cookie("username", user.getUserName());
		resp.addCookie(cookie);
		
		Cookie cookie1=new Cookie("unique", user.getUnique());
		resp.addCookie(cookie1);

		return "chat";
	}
}
