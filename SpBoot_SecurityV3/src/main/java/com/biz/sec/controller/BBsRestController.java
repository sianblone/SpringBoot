package com.biz.sec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.service.BBsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/bbs/api")
@RequiredArgsConstructor
@RestController
public class BBsRestController {
	
	private final BBsService bbsSvc;
	
	@CrossOrigin(value = "http://localhost:3000")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<BBsVO> getBbsList() {
		List<BBsVO> bbsList = bbsSvc.findAll();
		return bbsList;
	}
	
	@CrossOrigin(value = "http://localhost:3000")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public boolean insert(BBsVO bbsVO) {
		log.debug("게시판 데이터 : " + bbsVO.toString());
		bbsSvc.save(bbsVO);
		return true;
	}

}
