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
public class BookVO {
	private String book_id;  //도서 아이디
	private String library_id;  //도서관 아이디	
	private String borrow_id;  //대여 아이디
	private int book_type;  //도서 타입
	private String book_name;  //도서 제목
	private int price;  //가격
	private String buy_date;  //구입일	
	private String borrow_status;  //대여 상태
	private int borrow_count;  //빌린 횟수
	
	//도서 주문
	public BookVO(String library_id, int book_type, String book_name, int price) {
		super();
		this.library_id = library_id;
		this.book_type = book_type;
		this.book_name = book_name;
		this.price = price;
	}
}
