package com.dollars.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dollars.dao.UserMapper;
import com.dollars.entity.User;
import com.dollars.mybatis.MyBatisUtil;
import com.dollars.util.ResponseTools;

@Controller
public class ChangeHeadImgController {
	
	@RequestMapping("/changeimg.do")
	public void changeHeadImg(String id,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		Cookie[] cookies = req.getCookies();
		String userName = "";
		User user = new User();
		
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("username")){
				userName = cookies[i].getValue();
				userName = URLDecoder.decode(userName,"UTF-8");
				break;
			}
			if(i == cookies.length-1){
				ResponseTools.setMessage(resp, "CHANGE FAIL!");
			}
		}
		user.setUserName(userName);
		user.setHeadImgUrl("res/img/headimg/"+id+".png");
		
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.updateHeadImgByName(user);
		session.close();
		
		ResponseTools.setMessage(resp, "CHANGE SUCCESS!");
	}
}
