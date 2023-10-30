package com.lalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private String user_id;	//회원 아이디
	private String library_id;	//도서관 아이디
	private String user_name;	//회원 이름
	private String join_date;	//가입일
	private String loc;		//지역
	private String phone_no;	//연락처
	private int password;	//비밀번호
	
	//회원 가입
	public UserVO(String user_id, String library_id, String user_name, String loc, String phone_no, int password) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.user_name = user_name;
		this.library_id = library_id;
		this.loc = loc;
		this.phone_no = phone_no;
	}
	
	//로그인
	public UserVO(String user_name, int password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}
}
