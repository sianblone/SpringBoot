package com.biz.sec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserRole;
import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SecurityUserServiceImpl implements UserDetailsService {
	
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.debug("Username : " + username);
		Optional<UserVO> user = userDao.findByUsername(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException(username + " 정보를 찾을 수 없음");
		}
		
		UserVO userVO = userDao.findByUsername(username).get();
		
		Collection<GrantedAuthority> authorities = this.getUserAuthority(userVO.getUserRoles());
		userVO.setAuthorities(authorities);
		
		return userVO;
	}
	
	private Collection<GrantedAuthority> getUserAuthority(Set<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(UserRole userRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}
		return authorities;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return this.passwordEncoder;
	}
	
}