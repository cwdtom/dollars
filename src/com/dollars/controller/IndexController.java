package com.dollars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ��ҳ������
 * @author tom
 * 2015.12.14
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
