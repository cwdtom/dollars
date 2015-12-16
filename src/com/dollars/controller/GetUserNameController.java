package com.dollars.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dollars.dao.UserMapper;
import com.dollars.mybatis.MyBatisUtil;
import com.dollars.util.ResponseTools;

@Controller
public class GetUserNameController {
	
	@RequestMapping("/getusername.do")
	public void getUserName(String unique,HttpServletResponse resp) throws IOException{
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		String userName = mapper.selectNameByUnique(unique);
		session.close();
		
		ResponseTools.setMessage(resp, userName);
	}
}
