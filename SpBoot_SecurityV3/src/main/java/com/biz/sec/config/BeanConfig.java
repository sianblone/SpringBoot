package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class BeanConfig {
	
	// <bean></bean>을 대신하는 java 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// html 파일에서 sec: tag를 사용할 때 값, 설정, 함수 등을 사용할 수 있도록 객체를 전달하는 용도
	// thymeleaf-extras-springsecurity5 에서는 Security 설정된 Config에서 자동으로 주입된다
	// 특히 Config 클래스를 WebSecurityConfigurerAdapter 클래스를 상속받아서 작성할 경우 굳이 bean으로 설정하지 않아도 된다
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

}
