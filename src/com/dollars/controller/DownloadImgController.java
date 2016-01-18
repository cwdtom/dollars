package com.dollars.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dollars.util.AesUtil;
import com.dollars.util.DownloadImg;

@Controller
public class DownloadImgController {
	
	@RequestMapping("/download.do")
	public void downloadimg(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String url = request.getParameter("url");
		String name = request.getParameter("name");
		
		name = AesUtil.decrypt(name, "password");
		DownloadImg downloadImg = new DownloadImg(url, name, "d://images/");
		downloadImg.run();
		
		PrintWriter out = response.getWriter();
		out.print("200");
		out.close();
	}
}
