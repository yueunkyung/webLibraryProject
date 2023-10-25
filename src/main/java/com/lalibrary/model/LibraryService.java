package com.lalibrary.model;

import java.util.List;

import com.lalibrary.dto.BookVO;
import com.lalibrary.dto.BorrowBookVO;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.dto.LostAndFoundVO;
import com.lalibrary.dto.UserVO;

public class LibraryService {
	LibraryDAO dao = new LibraryDAO();

	//통합도서검색-도서 이름으로 검색하기
	public List<BookVO> selectByBookTitle(String title){
		return dao.selectByBookTitle(title);
	}
	
	//도서 주문
	public int insertOrderBook(BookVO book) {		
		return dao.insertOrderBook(book);
	}
	
	//도서 대여
	public int insertBorrowBook(BorrowBookVO borrowBook) {		
		return dao.insertBorrowBook(borrowBook);
	}
	
	//도서 전체 조회
	public List<BookVO> selectBookAll(){
		return dao.selectBookAll();
	}
	
	//도서관 전체 조회
	public List<LibraryVO> selectLibraryAll(){
		return dao.selectLibraryAll();
	}
	
	//도서관별 도서 전체 조회
	public List<BookVO> selectEachLibraryBook(String libId){
		return dao.selectEachLibraryBook(libId);
	}	
	
	//인기 도서 조회
	public List<BookVO> selectPopularBook(int rank){
		return dao.selectPopularBook(rank);
	}

	//회원 전체 조회
	public List<UserVO> selectUser(String target, String sqlParam){
		return dao.selectUser(target, sqlParam);
	}
	
	//회원 가입
	public int insertSignUp(UserVO memberInfo) {
		return dao.insertSignUp(memberInfo);
	}

	//분실물 보관센터
	public List<LostAndFoundVO> selectLostAndFound(String target, String sqlParam){
		return dao.selectLostAndFound(target, sqlParam);
	}

	//분실물 등록
	public int insertLostItem(LostAndFoundVO itemInfo) {
		return dao.insertLostItem(itemInfo);
	}
	
	//분실물 수령
	public int deleteLostItem(LostAndFoundVO itemId) {
		return dao.deleteLostItem(itemId);
	}
}
