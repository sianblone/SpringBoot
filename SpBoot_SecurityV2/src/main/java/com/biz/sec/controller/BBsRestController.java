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

/*
 * Rest Controller
 * Spring RESTful API를 만들기 위한 Controller
 * view가 없는 형태로 json 형태의 데이터를 보내주는 컨트롤러 역할
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/bbs/api")
@RestController
public class BBsRestController {
	
	private final BBsService bbsSvc;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "list", method=RequestMethod.GET)
	public List<BBsVO> getBbsList() {
		return bbsSvc.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public boolean insert(BBsVO bbsVO) {
		log.debug(bbsVO.toString());
		BBsVO ret = bbsSvc.save(bbsVO);
		return true;
	}

}
