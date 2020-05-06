package com.biz.sec.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserDao userDao;
	private final PasswordEncoder bCrypt;
	
	// 유저정보 DB 저장 메소드
	// 비밀번호 bCrypt로 암호화해서 저장하기
	public void save(Optional<UserVO> opUserVO) {
		UserVO userVO = opUserVO.get();
		String strPW = userVO.getPassword();
		String encPW = bCrypt.encode(strPW);
		
		userVO.setPassword(encPW);
		userVO.setEnabled(true);
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		
		UserVO retUserVO = userDao.save(userVO);
	}
	
	

}
