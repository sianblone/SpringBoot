package com.biz.sec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.repository.BBsDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BBsService {
	
	private final BBsDao bbsDao;
	
	public Page<BBsVO> getPageList(Pageable pageable) {
		
		// 현재 페이지 번호 가져오기
		int page = 0;
		if(pageable.getPageNumber() != 0) {
			page = pageable.getPageNumber() - 1;
		}
		
		// 페이지 번호를 기준으로 10개의 데이터를 추출하기 위한 설정값
		// 현재 페이지가 1이라면 0~9까지의 데이터 가져오기, 현재 페이지가 2페이지라면 10~19까지 데이터 가져오기
		pageable = PageRequest.of(page, 10);
		
		// 페이지를 가져오기 위한 설정 객체(pageable)를 Dao에게 전달하여 한 페이지의 데이터만 가져오기
		Page<BBsVO> pageBbsList = bbsDao.findAll(pageable);
		
		return pageBbsList;
	}
	
	public List<BBsVO> findAll() {
		return bbsDao.findAll();
	}

	public BBsVO save(BBsVO bbsVO) {
		return bbsDao.save(bbsVO);
	}

}
