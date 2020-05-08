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
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public BBsVO detail(Long bbs_id) {
		BBsVO bbsVO = bbsSvc.findById(bbs_id);
		return bbsVO;
	}
	
	@CrossOrigin(value = "http://localhost:3000")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public boolean save(BBsVO bbsVO) {
		log.debug("게시판 데이터 : " + bbsVO.toString());
		BBsVO returnVO = bbsSvc.save(bbsVO);
		if(returnVO == null) {
			return false;
		}
		return true;
	}
	
	@CrossOrigin(value = "http://localhost:3000")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean delete(long bbs_id) {
		bbsSvc.delete(bbs_id);
		
		return true;
	}

}
