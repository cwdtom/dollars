package com.dollars.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.dollars.dao.UserMapper;
import com.dollars.mybatis.MyBatisUtil;
import com.dollars.util.Md5Util;

/**
 * Ð£Ñé¼¤»îÂë
 * @author tom
 * 2015.12.15
 */
public class AccessCodeUtil {
	
	public static boolean isAccessCode(String accessCode) throws IOException{
		boolean result = false;
		SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
		Integer now = Integer.parseInt(sdf.format(new Date()));
		
		for(int i=1;i<11;i++){
			String code = Md5Util.md5(now*i+"");
			if(code.equals(accessCode)){
				
				SqlSession session = MyBatisUtil.getSession();
				UserMapper mapper = session.getMapper(UserMapper.class);
				String userId = mapper.selectUserByAccessCode(accessCode);
				session.close();
				
				if(userId == null){
					result = true;
				}
				break;
			}
		}
		
		return result;
	}
}
