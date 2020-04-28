package com.biz.sec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.sec.domain.UserVO;

public interface UserDao extends JpaRepository<UserVO, Long>{
	
	/*
	 * JPA, Hibernate를 사용하면 Entity 클래스(VO 클래스에 @Entity 어노테이션이 부착된 클래스)에 적용하여
	 * findByVariable method를 자동으로 생성해주는 기능이 있다
	 * 
	 * Optional<T> : NPE(NullPointerException) 발생 방지 클래스, 자바 1.8 이상
	 */
	public Optional<UserVO> findByUsername(String username);
	
	
	
}
