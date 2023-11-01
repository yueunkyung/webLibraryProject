package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lalibrary.dto.BookVO;
import com.lalibrary.util.DBUtil;

public class BookDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	//통합도서검색
	public List<BookVO> selectByBook(String libId, String searchName){
		List<BookVO> booklist = new ArrayList<>();
		String sql = "select * from books join lalibrary using(library_id)";
		if(libId != null && searchName!= null) {
			sql = "select * from books join lalibrary using(library_id)"
					+ " where library_id = '"+libId+"'" + " and book_name like '%" + searchName + "%'";	
		} else if(libId != null) {
			sql = "select * from books join lalibrary using(library_id)"
					+ " where library_id = '"+libId+"'";
		} else if(searchName != null) {
			sql = "select * from books join lalibrary using(library_id)"
					+ " where book_name like '%" + searchName + "%'";
		}
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
	
	//user 대출 도서 조회
	public List<BookVO> selectByBook(String userId){
		List<BookVO> booklist = new ArrayList<>();
		String sql = "select"
				+ "   books.book_name"
				+ "  ,books.book_id"
				+ "  ,lalibrary.library_name"
				+ "  ,books.book_type"
				+ " from users"
				+ " join borrowed_books"
				+ "  on users.user_id = borrowed_books.user_id"
				+ " join books"
				+ "  on borrowed_books.book_id = books.book_id"
				+ " join lalibrary"
				+ "  on books.library_id = lalibrary.library_id"
				+ " where users.user_id = '"+userId+"'";
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BookVO book = makeBorrowedBookList(rs);//reset에서 읽어서 VO만들기
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return booklist;
	}

	//도서 상세 정보
	public BookVO selectDetailBook(String bookId){
		BookVO book = null;
		String sql = "select books.book_name"
				+ ", books.book_id"
				+ ", books.book_type"
				+ ", LALIBRARY.library_name"
				+ ", books.borrow_status"
				+ ", borrowed_books.return_date"
				+ " from books join lalibrary on books.library_id = lalibrary.library_id"
				+ "            left outer join borrowed_books on books.book_id = borrowed_books.book_id"
				+ " where books.book_id = '"+bookId+"'";
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				book = makeBookDetail(rs);
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
	
	//BookVO
	private BookVO makeBookList(ResultSet rs) throws SQLException {
		BookVO book = new BookVO();
		int index = 0;		

		book.setLibrary_id(rs.getString(++index));
		book.setBook_id( rs.getString(++index));
		book.setBorrow_id(rs.getString(++index));
		book.setBook_type(rs.getInt(++index));
		book.setBook_name(rs.getString(++index));
		book.setPrice(rs.getInt(++index));
		book.setBuy_date(rs.getString(++index));
		book.setBorrow_status (rs.getString(++index));
		book.setBorrow_count (rs.getInt(++index));
		book.setLibrary_name (rs.getString(++index));
		book.setLoc (rs.getString(++index));
		book.setMember_count (rs.getInt(++index));
		
		return book;
	}
	
	//BookVO(user 대출 도서 조회)
	private BookVO makeBorrowedBookList(ResultSet rs) throws SQLException {
		BookVO book = new BookVO();
		int index = 0;
		
		book.setBook_name( rs.getString(++index));
		book.setBook_id( rs.getString(++index));
		book.setLibrary_name(rs.getString(++index));
		book.setBook_type(rs.getInt(++index));
		
		return book;
	}
	
	//BookVO(도서 상세 정보)
	private BookVO makeBookDetail(ResultSet rs) throws SQLException {
		BookVO book = new BookVO();
		int index = 0;
		
		book.setBook_name( rs.getString(++index));
		book.setBook_id(rs.getString(++index));
		book.setBook_type(rs.getInt(++index));
		book.setLibrary_name(rs.getString(++index));
		book.setBorrow_status(rs.getString(++index));
		book.setReturn_date(rs.getDate(++index));
		
		return book;
	}
}

	
