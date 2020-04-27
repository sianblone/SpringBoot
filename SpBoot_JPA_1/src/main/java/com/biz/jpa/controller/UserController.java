package com.biz.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.jpa.domain.UserVO;
import com.biz.jpa.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/user")
@Controller
public class UserController {
	
	private final UserService userSvc;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userList(Model model) {
		List<UserVO> userList = userSvc.selectAll();
		model.addAttribute("userList", userList);
		return "layout";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		model.addAttribute("user", new UserVO());
		return "usersave";
	}
	
	// Spring boot에서는 jackson-bind 등의 디펜던시가 없어도 @ResponseBody를 통해 VO 클래스를 JSON 형식으로 볼 수 있다
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(UserVO userVO) {
		UserVO returnVO = userSvc.save(userVO);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public String update(long id, Model model) {
		UserVO userVO = userSvc.findById(id);
		model.addAttribute("user", userVO);
		return "usersave";
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public String update(UserVO userVO) {
		UserVO returnVO = userSvc.save(userVO);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(long id) {
		userSvc.delete(id);
		return "redirect:/user/list";
	}

}
