package com.biz.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

// JPA를 사용한 DB 설정
// Entity는 javax.persistence.Entity로 설정한다
@Entity
@Table(name="tbl_users", schema="spboot")
public class UserVO {
	
	/*
	 * @GeneratedValue의 strategy를 AUTO로 설정하면 JPA가 임의로 테이블을 생성하고 sequence를 만들어서 주입하도록 설정된다
	 * Oracle, MySQL 등 DBMS에 관계없이 자동 생성 칼럼의 값을 주입한다
	 *
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE) : AUTO_INCREMENT 옵션이 없는 DBMS에서 사용(Oracle 등)
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) : AUTO_INCREMENT 옵션이 있는 DBMS에서 사용(MySQL, MSSQL, MariaDB 등)
	 * @GeneratedValue(strategy = GenerationType.TABLE) : 키 생성 전용 테이블을 하나 만들고 이를 사용하여 기본키 생성
	 * @GeneratedValue(strategy = GenerationType.AUTO) : JPA 구현체가 자동으로 결정
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// AUTO_INCREMENT로 설정
	private long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;

}
