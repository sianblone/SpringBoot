package com.biz.sec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;

// Spring Security의 customizing 첫번째 단계
// MVC의 security-context.xml에 설정했던 옵션을 지정하는 클래스
@RequiredArgsConstructor
@Configuration
// @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final SecurityUserServiceImpl sUserSvc;
	private final PasswordEncoder passwordEncoder;
	
	//매개변수 WebSecurity = 시큐리티에서 검사하지 않을 주소 설정
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(web);
		web.ignoring()
			.antMatchers("/static/css/**", "/static/js/**")
			.antMatchers("/static/images/**")
			.antMatchers("/static/music/**");
	}
	
	
	//매개변수 HttpSecurity = 시큐리티에서 검사할 위치를 설정
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		//React와 연동할 때 security 설정
		httpSecurity
		.csrf().disable()//CSRF 기능 끄기
		.httpBasic().disable()//권한이 없을 때 자동으로 login form이 뜨지않도록 하기
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션을 사용한 Security 정지
		;
		
		httpSecurity.authorizeRequests()
				.antMatchers("/hello").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user/login").permitAll()
				.antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests()
				.and()
				.formLogin()
				.loginPage("/user/login")
				.failureUrl("/user/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
				.logout()
				.logoutSuccessUrl("/");
	}
	
	// 매개변수 AuthenticationManagerBuilder = 시큐리티에서 UserDetailsService와 PasswordEncoder를 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		auth.userDetailsService(sUserSvc).passwordEncoder(passwordEncoder);
	}

}
