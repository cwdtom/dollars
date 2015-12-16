package com.dollars.dao;

import com.dollars.entity.User;

public interface UserMapper {
	
	//按用户名查找
	public String selectUserByName(String userName);
	
	//按AccessCode查找
	public String selectUserByAccessCode(String accessCode);
	
	//增加一个新用户
	public void insertUser(User user);
	
	//查询用户账户密码是否正确
	public String selectUserByPassWord(User user);
	
	//按userName查询返回User
	public User selectAllByName(String userName);
	
	//按userName修改headImgUrl
	public void updateHeadImgByName(User user);
	
	//按userName修改passWord
	public void updatePassWordByName(User user);
	
	//按unique查找userName
	public String selectNameByUnique(String unique);
}
