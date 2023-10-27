package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lalibrary.dto.BookVO;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.util.DBUtil;

public class BookDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	//통합도서검색-도서 이름 검색
	public List<BookVO> selectByBookTitle(String title){
		List<BookVO> booklist = new ArrayList<>();
		String sql = "SELECT * FROM BOOKS WHERE BOOK_NAME LIKE '%" + title + "%'";
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BookVO book = makeBookList(rs);//reset에서 읽어서 VO만들기
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return booklist;
	};

	//도서 대여
	public int insertBorrowBook(BookVO borrowInfo) {
		//procedure - 도서 대여 목록 추가 처리 
		//books 업데이트 TRIGGER 적용
		int count = 0;
		String sql = "{call add_new_borrow(?, ?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, borrowInfo.getBook_id());
			pst.setString(2, borrowInfo.getUser_id());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}

		return count;
	}
	
	//도서 주문
	public int insertOrderBook(BookVO bookInfo) {
		//procedure - 책(books) 추가 처리
		int count = 0;
		String sql = "{call add_new_book(?, ?, ?, ?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, bookInfo.getLibrary_id());
			pst.setInt(2, bookInfo.getBook_type());
			pst.setString(3, bookInfo.getBook_name());
			pst.setInt(4, bookInfo.getPrice());

			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DBUtil.dbDisConnection(conn, pst, rs);
		}
				
		return count;		
	}
	
	//도서 전체 조회
	public List<BookVO> selectBookAll(){
		List<BookVO> bookAll = new ArrayList<>();
		String sql = "select * from books";
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BookVO book = makeBookList(rs);//reset에서 읽어서 VO만들기
				System.out.println(book);
				bookAll.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return bookAll;
	}
	
	//도서관 전체 조회
	public List<LibraryVO> selectLibraryAll(){
		List<LibraryVO> libraryAll = new ArrayList<>();
		String sql = "select * from lalibrary";
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				LibraryVO library = makeLalibraryList(rs);//reset에서 읽어서 VO만들기
				libraryAll.add(library);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return libraryAll;
	}
	
	//도서관별 도서 전체 조회
	public List<BookVO> selectEachLibraryBook(String libId){
		List<BookVO> book = new ArrayList<>();
		String sql = "select * from books where library_id = '"+libId+"'";
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BookVO libraryBookAll = makeBookList(rs);//reset에서 읽어서 VO만들기
				book.add(libraryBookAll);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return book;
	}

	//인기 도서 조회
	public List<BookVO> selectPopularBook(int rank){
		List<BookVO> bestlist = new ArrayList<>();
		String sql = "select *"
				+ " from ( select * from books"
				+ "       order by BORROW_COUNT desc nulls last)"
				+ " where rownum <=" + rank;
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {		
				BookVO bestbook = makeBookList(rs);//reset에서 읽어서 VO만들기
				bestlist.add(bestbook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return bestlist;
	}
	

	
	//도서-BookVO
	private BookVO makeBookList(ResultSet rs) throws SQLException {
		BookVO book = new BookVO();
		book.setBook_id( rs.getString(1));
		book.setLibrary_id(rs.getString(2));
		book.setBorrow_id(rs.getString(3));
		book.setBook_type(rs.getInt(4));
		book.setBook_name(rs.getString(5));
		book.setPrice(rs.getInt(6));
		book.setBuy_date(rs.getString(7));
		book.setBorrow_status (rs.getString(8));
		//book.setBorrow_count (rs.getInt(9));
		
		return book;
	}

	//도서관-LibraryVO
	private LibraryVO makeLalibraryList(ResultSet rs) throws SQLException{
		LibraryVO libr = new LibraryVO();
		libr.setLibrary_id(rs.getString(1));
		libr.setLibrary_name(rs.getString(2));
		libr.setLoc(rs.getString(3));
		libr.setMember_count(rs.getInt(4));
		
		return libr;
	}

}

	
