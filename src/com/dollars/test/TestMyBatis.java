package com.dollars.test;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.dollars.dao.UserMapper;
import com.dollars.mybatis.MyBatisUtil;

public class TestMyBatis {
	
	@Test
	public void testGetUserId() throws IOException{
		String userName = "server";
		
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		String userId = mapper.selectUserByName(userName);
		session.close();
		
		System.out.println(userId);
	}
}
