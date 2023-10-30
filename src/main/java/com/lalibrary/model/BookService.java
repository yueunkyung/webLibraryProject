package com.lalibrary.model;

import java.util.List;

import com.lalibrary.dto.BookVO;

public class BookService {
	BookDAO dao = new BookDAO();

	// 통합도서검색
	public List<BookVO> selectByBook(String libId, String searchName) {
		return dao.selectByBook(libId, searchName);
	}
	
	//user 대출 도서 조회
	public List<BookVO> selectByBook(String userId){
		return dao.selectByBook(userId);
	}

	// 도서 주문
	public int insertOrderBook(BookVO book) {
		return dao.insertOrderBook(book);
	}

	// 도서 대여
	public int insertBorrowBook(BookVO borrowBook) {
		return dao.insertBorrowBook(borrowBook);
	}

	// 인기 도서 조회
	public List<BookVO> selectPopularBook(int rank) {
		return dao.selectPopularBook(rank);
	}

}
