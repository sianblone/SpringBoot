package com.biz.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.hello.domain.UserVO;

@Service
public class UserService {
	
	public List<UserVO> getUserList() {
		List<UserVO> userList = new ArrayList<>();
		
		userList.add( UserVO.builder()
				.username("admin")
				.password("admin")
				.email("a@a")
				.phone("010-1111-1111")
				.address("관리자주소")
				.build()
		);
		
		userList.add( UserVO.builder()
				.username("user")
				.password("user")
				.email("u@u")
				.phone("010-2222-2222")
				.address("유저주소")
				.build()
		);
		
		return userList;
	}

}
