package com.dollars.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.dollars.util.Md5Util;

public class TestAccessCode {
	
	@Test
	public void testAccessCode(){
		SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
		Integer now = Integer.parseInt(sdf.format(new Date()));
		
		for(int i=1;i<11;i++){
			String code = Md5Util.md5(now*i+"");
			System.out.println(code);
		}
	}
}
