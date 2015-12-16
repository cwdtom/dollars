package com.dollars.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理Response各种工具
 * @author tom
 *
 */
public class ResponseTools {
	/**
	 * 向response中添加一条信息
	 * @param HttpServletResponse response
	 * @param String message
	 */
	public static void setMessage(HttpServletResponse response,String message){
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try{
			PrintWriter out = response.getWriter();
			out.println(message);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
