package com.lalibrary.model;

import java.util.List;

import com.lalibrary.dto.BookVO;
//import com.lalibrary.dto.BorrowBookVO;
import com.lalibrary.dto.LibraryVO;

public class BookService {
	BookDAO dao = new BookDAO();

	// 통합도서검색-도서 이름으로 검색하기
	public List<BookVO> selectByBookTitle(String title) {
		return dao.selectByBookTitle(title);
	}

	// 도서 주문
	public int insertOrderBook(BookVO book) {
		return dao.insertOrderBook(book);
	}

	// 도서 대여
	public int insertBorrowBook(BookVO borrowBook) {
		return dao.insertBorrowBook(borrowBook);
	}

	// 도서 전체 조회
	public List<BookVO> selectBookAll() {
		return dao.selectBookAll();
	}

	// 도서관별 도서 전체 조회
	public List<BookVO> selectEachLibraryBook(String libId) {
		return dao.selectEachLibraryBook(libId);
	}

	// 인기 도서 조회
	public List<BookVO> selectPopularBook(int rank) {
		return dao.selectPopularBook(rank);
	}

}
