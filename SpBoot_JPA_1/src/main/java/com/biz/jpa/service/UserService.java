package com.biz.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.jpa.domain.UserVO;
import com.biz.jpa.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userDao;
	
	public UserService(UserRepository userDao) {
		super();
		this.userDao = userDao;
	}

	public UserVO save(UserVO userVO) {
		/*
		 * repository.save(VO)
		 * JPA 환경에서는 JpaRepository를 extends하여 사용하기에
		 * 자동으로 기본 CRUD method를 사용할 수 있다
		 */
		UserVO userReturn = userDao.save(userVO);
		return userReturn;
	}

	public List<UserVO> selectAll() {
		return userDao.findAll();
	}
	
	/*
	 * Optional<> : Java 1.8 이상에서 사용 가능한 객체
	 * 
	 * findById를 실행하면 UserVO를 받을 수 있고 결과가 없으면 null을 리턴한다
	 * null을 return했을 경우 NPE(NullPointerException)이 발생할 수 있다
	 * Optional(wrapper class)로 VO 객체를 감싸게 되면 NullPointerException을 피할 수 있다
	 * 실제 필요한 VO 객체는 .get() 메소드를 사용하여 가져온다
	 */
	public UserVO findById(long id) {
		return userDao.findById(id).get();
	}

	public void delete(long id) {
		userDao.deleteById(id);
	}

}
