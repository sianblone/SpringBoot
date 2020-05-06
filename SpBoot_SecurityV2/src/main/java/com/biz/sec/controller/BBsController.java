package com.biz.sec.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.service.BBsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/bbs")
@Controller
public class BBsController {
	
	private final BBsService bbsSvc;
	
	/*
	 * JPA를 사용한 pagination
	 * 1. list 메소드에 Pageable 매개변수 선언
	 */
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(@PageableDefault Pageable pageable, Model model) {
		Page<BBsVO> bbsList = bbsSvc.getPageList(pageable);
		model.addAttribute("BBS_LIST", bbsList);
		
		return "bbs_list";
	}

}
