package com.biz.hello.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String hello(Model model) {
		Map<String, String> user = new HashMap<>();
		user.put("name", "이름");
		user.put("tel", "010");
		model.addAttribute("user", user);
		
		return "hello";
	}

}
