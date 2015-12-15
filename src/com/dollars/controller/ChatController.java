package com.dollars.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dollars.dao.UserMapper;
import com.dollars.mybatis.MyBatisUtil;

/**
 * �����ҿ�����
 * @author tom
 * 2015.12.12
 */
@Controller
public class ChatController {
	
	@RequestMapping("/chat.do")
	public String chat(HttpServletRequest req) throws IOException{
		Cookie[] cookies = req.getCookies();
		String userName = "";
		String unique = "";
		
		//�ж��Ƿ��¼��û�е�¼ֱ����ת����ҳ
		if(cookies == null){
			return "index";
		}
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("username")){
				userName = cookies[i].getValue();
				break;
			}
			if(i == cookies.length-1){
				return "index";
			}
		}
		
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("unique")){
				unique = cookies[i].getValue();
				break;
			}
			if(i == cookies.length-1){
				return "index";
			}
		}
		
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		if(!mapper.selectAllByName(userName).getUnique().equals(unique)){
			return "index";
		}
		session.close();
		
		return "chat";
	}
}
