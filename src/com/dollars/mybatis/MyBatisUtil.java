package com.dollars.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	/**
	 * 获取连接池连接
	 * @param Boolean commit 是否开启自动提交
	 * @return SqlSession session
	 * @throws IOException
	 */
	public static SqlSession getSession(Boolean commit) throws IOException{
		String resource = "config/conf.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession(commit);
		
		return session;
	}
	
	/**
	 * 获取连接池连接
	 * @return session
	 * @throws IOException
	 */
	public static SqlSession getSession() throws IOException{
		String resource = "config/conf.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession(true);
		
		return session;
	}
}
