package com.biz.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.jpa.dao.BookDao;
import com.biz.jpa.domain.BookVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
	
	private final BookDao bookDao;
	
	/*
	 * spring-data 패키지의
	 * Pageable, PageRequest, Page<> 클래스를 사용하여 pagination 구현하기 
	 */
	public Page<BookVO> getPageList(Pageable page) {
		int pageNum = page.getPageNumber() == 0 ? pageNum = 0 : page.getPageNumber() - 1;
		page = PageRequest.of(pageNum, 10);
		return bookDao.findAll(page);
	}
	
	public List<BookVO> findAll() {
		return bookDao.findAll();
	}

	public BookVO findById(long longid) {
		return bookDao.findById(longid).get();
	}

	public BookVO save(BookVO bookVO) {
		return bookDao.save(bookVO);
	}

	public void deleteById(long longid) {
		bookDao.deleteById(longid);
	}

}
