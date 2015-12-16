package com.dollars.test;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.dollars.dao.UserMapper;
import com.dollars.entity.User;
import com.dollars.mybatis.MyBatisUtil;
import com.dollars.util.Md5Util;

public class ChangePassWord {
	
	@Test
	public void changePassWord() throws IOException{
		User user = new User();
		user.setUserName("惴惴不安的新人");
		user.setPassWord(Md5Util.md5("111111"));
		
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.updatePassWordByName(user);
		session.close();
		
		System.out.println("success");
	}
}
