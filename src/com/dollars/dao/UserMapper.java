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
	
	//��userName�޸�headImgUrl
	public void updateHeadImgByName(User user);
	
	//��userName�޸�passWord
	public void updatePassWordByName(User user);
	
	//��unique����userName
	public String selectNameByUnique(String unique);
}
