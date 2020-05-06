package com.biz.sec;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.BBsDao;

@SpringBootApplication
public class SpBootSecurityV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpBootSecurityV1Application.class, args);
	}
	
	// Spring Boot에서 프로젝트가 시작될 때 실행할 일들을 작성하는 방법
	// bean만 빼줘도 작동하지 않는다
	//@Bean
	public CommandLineRunner initBbsData(BBsDao bbsDao) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				for(int i = 0 ; i < 100 ; i++) {
					LocalDate localDate = LocalDate.now();
					LocalTime localTime = LocalTime.now();
					String title = String.format("현재 : %s", localTime.toString());
					String date = localDate.toString();
					
					BBsVO bbsVO = BBsVO.builder()
								.bbsTitle(title)
								.bbsDate(date)
								.bbsAuth("글쓴이")
								.bbsText("내용")
								.build();
					bbsDao.save(bbsVO);
				}
			}
		};
	}

}
