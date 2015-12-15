package com.dollars.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dollars.dao.UserMapper;
import com.dollars.entity.User;
import com.dollars.mybatis.MyBatisUtil;
import com.dollars.util.AccessCodeUtil;
import com.dollars.util.Md5Util;

/**
 * ×¢²á¿ØÖÆÆ÷
 * @author tom
 * 2015.12.15
 */
@Controller
public class RegisterController {
	
	@RequestMapping("register.do")
	public void register(HttpServletResponse resp,HttpServletRequest req) throws IOException{
		User user = new User();
		user.setUserName(req.getParameter("username"));
		user.setPassWord(req.getParameter("password"));
		user.setAccessCode(req.getParameter("accesscode"));
		String result = "";
		
		if(!AccessCodeUtil.isAccessCode(user.getAccessCode())){
			result = "ERROR ACCESSCODE";
		}else if(!user.getPassWord().equals(req.getParameter("repassword"))){
			result = "PASSWORD IS NOT SAME";
		}else{

			//select username from user where username = username
			SqlSession session = MyBatisUtil.getSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			String userId = mapper.selectUserByName(user.getUserName());
			
			if(userId != null){
				result = "USERNAME IS EXISTED";
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
				Integer now = Integer.parseInt(sdf.format(new Date()));
				String unique = Md5Util.md5(now+"@");
				user.setUnique(unique);
				user.setHeadImgUrl("0");
				user.setPassWord(Md5Util.md5(user.getPassWord()));
				
				//insert user (username,password,accesscode) value (username,password,accesscode)
				mapper.insertUser(user);
				session.close();
				result="REGISTER IS SUCCESS";
			}
		}
		PrintWriter out = resp.getWriter();
		out.print(result);
		out.close();
	}
}
