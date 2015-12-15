package com.dollars.dao;

import com.dollars.entity.User;

public interface UserMapper {
	
	//���û�������
	public String selectUserByName(String userName);
	
	//��AccessCode����
	public String selectUserByAccessCode(String accessCode);
	
	//����һ�����û�
	public void insertUser(User user);
	
	//��ѯ�û��˻������Ƿ���ȷ
	public String selectUserByPassWord(User user);
	
	//��userName��ѯ����User
	public User selectAllByName(String userName);
}
