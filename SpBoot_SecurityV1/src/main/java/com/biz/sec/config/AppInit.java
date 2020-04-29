package com.biz.sec.config;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.biz.sec.repository.UserDao;
import com.biz.sec.repository.UserRoleDao;

import lombok.RequiredArgsConstructor;

/*
 * CommandLineRunner 인터페이스를 상속받은 클래스
 * spring boot에서만 사용할 수 있는 특별한 클래스가 되는데
 * 프로젝트가 시작되는 시점에 어떤 코드를 자동으로 실행하고 싶을 때 작성하는 클래스
 */
/*
@RequiredArgsConstructor
@Component
public class AppInit implements CommandLineRunner {
	
	private final UserDao userDao;
	private final UserRoleDao userRoleDao;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		
		// Optional 클래스로 vo 클래스를 wrapping 했을 때
		// 데이터가 있으면 isPresent()는 true를 리턴한다
		Optional<UserVO> checkVO = userDao.findByUsername("admin");
		if(checkVO.isPresent()) return;
		
		UserVO userVO = UserVO.builder()
				.username("admin")
				.password("admin")
				.enabled(true)
				.build();
		
		userDao.save(userVO);
		
		UserRole userRole = UserRole.builder()
				.username("admin")
				.roleName("ROLE_ADMIN")
				.build();
		userRoleDao.save(userRole);
		
		userRole = UserRole.builder()
				.username("admin")
				.roleName("ROLE_USER")
				.build();
		userRoleDao.save(userRole);
	}
	
}
*/
