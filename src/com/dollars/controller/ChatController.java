package com.dollars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * �����ҿ�����
 * @author tom
 * 2015.12.12
 */
@Controller
public class ChatController {
	
	@RequestMapping("/chat.do")
	public String chat(){
		return "chat";
	}
}
