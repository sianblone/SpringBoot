package com.biz.sec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserRole;
import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {
	
	private final UserDao userDao;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userDao.findByUsername(username).get();
		
		Collection<GrantedAuthority> authorities = this.getUserAuthority(userVO.getUserRoles());
		return null;
	}
	
	private Collection<GrantedAuthority> getUserAuthority(Set<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(UserRole userRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}
		return authorities;
	}

}