package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lalibrary.dto.BookVO;
import com.lalibrary.dto.BorrowBookVO;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.dto.LostAndFoundVO;
import com.lalibrary.dto.UserVO;
import com.lalibrary.util.DBUtil;

public class LibraryDAO {
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
	public int insertBorrowBook(BorrowBookVO borrowInfo) {
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
	
	//회원 조회
	public List<UserVO> selectUser(String target, String sqlParam){
		List<UserVO> showuser = new ArrayList<>();
		String sql = null;
		switch (target) {
			case "all": {
				sql = "select * from users";
				break;
			}
			case "user_name": {
				sql = "select * from users where user_name LIKE '%" + sqlParam + "%'";
				break;
			}
			case "user_id": {
				sql = "select * from users where user_id = '"+sqlParam+"'" ;
				break;
			}
			case "library_id": {
				sql = "select * from users where library_id = '"+sqlParam+"'" ;
				break;
			}
		}
			
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {		
				UserVO users = makeUserList(rs);//reset에서 읽어서 VO만들기
				showuser.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return showuser;
	}

	//회원 가입
	public int insertSignUp(UserVO memberInfo) {
		int count = 0;
		String sql = "{call add_new_user(?, ?, ?, ?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, memberInfo.getLibrary_id());
			pst.setString(2, memberInfo.getUser_name());
			pst.setString(3, memberInfo.getLoc());
			pst.setString(4, memberInfo.getPhone_no());
			
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
				
		return count;		
	}

	//분실물 보관센터-분실물 조회
	public List<LostAndFoundVO> selectLostAndFound(String target, String sqlParam){
		List<LostAndFoundVO> showlist = new ArrayList<>();
		String sql = null;
		switch (target) {
			case "all": {
				sql = "select * from lost_and_found";
				break;
			}
			case "property_name": {
				sql = "select * from lost_and_found where property_name LIKE '%" + sqlParam + "%'";
				break;
			}
		}
		conn = DBUtil.getConnection();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				LostAndFoundVO item = makeLostItemList(rs);//reset에서 읽어서 VO만들기
				showlist.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return showlist;
	};

	//분실물 등록
	public int insertLostItem(LostAndFoundVO itemInfo) {
		int count = 0;
		String sql = "{call add_new_lost(?,?,?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, itemInfo.getLibrary_id());
			pst.setString(2, itemInfo.getProperty_name());
			pst.setString(3, itemInfo.getFound_date());
			
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
				
		return count;		
	}
	
	//분실물 수령
	public int deleteLostItem(LostAndFoundVO itemId) {
		int count = 0;
		String sql = "delete from lost_and_found where property_id = ?";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);			
			pst.setString(1, itemId.getProperty_id());
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
				
		return count;		
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
		book.setBorrow_count (rs.getInt(9));
		
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

	//회원-UserVO
	private UserVO makeUserList(ResultSet rs) throws SQLException {
		UserVO user = new UserVO();
		user.setUser_id( rs.getString(1));
		user.setLibrary_id(rs.getString(2));
		user.setUser_name(rs.getString(3));
		user.setJoin_date(rs.getString(4));
		user.setLoc(rs.getString(5));
		user.setPhone_no(rs.getString(6));
		
		return user;
	}

	//분실물 보관센터-LostAndFoundVO
	private LostAndFoundVO makeLostItemList(ResultSet rs) throws SQLException {
		LostAndFoundVO lostItem = new LostAndFoundVO();
		lostItem.setProperty_id( rs.getString(1));
		lostItem.setLibrary_id(rs.getString(2));
		lostItem.setProperty_name(rs.getString(3));
		lostItem.setFound_date(rs.getString(4));
		
		return lostItem;
	}

}

	
