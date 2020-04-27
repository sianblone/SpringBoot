package com.biz.jpa.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.jpa.domain.BookVO;
import com.biz.jpa.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/book")
@Controller
public class BookController {
	
	private final BookService bookSvc;
	
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String list(Model model) {
		
		List<BookVO> bookList = bookSvc.findAll();
		model.addAttribute("BOOK_LIST", bookList);
		return "book_list";
	}
	
	@RequestMapping(value = "/pagelist", method=RequestMethod.GET)
	public String page(@PageableDefault Pageable page, Model model) {
		Page<BookVO> pageList = bookSvc.getPageList(page);
		model.addAttribute("BOOK_LIST", pageList);
		return "book_list";
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.GET)
	public String save(String id, Model model) {
		if(id != null && id != "") {
			long longid = 0;
			
			try {
				longid = Long.parseLong(id);
			} catch (Exception e) {
			}
			
			BookVO bookVO = bookSvc.findById(longid);
			model.addAttribute("BOOK", bookVO);
		} else {
			model.addAttribute("BOOK", new BookVO());
		}
		return "book_save";
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public String save(BookVO bookVO) {
		bookSvc.save(bookVO);
		return "redirect:/book";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public String delete(String id) {
		if(id != null && id != "") {
			long longid = 0;
			try {
				longid = Long.parseLong(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			bookSvc.deleteById(longid);
		}
		
		return "redirect:/book";
	}

}
