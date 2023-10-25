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
public class LibraryVO {
	private String library_id;  //도서관 아이디
	private String library_name;  //도서관 이름
	private String loc;  //지역
	private int member_count;  //회원수
}
