package com.lalibrary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lalibrary.dto.BookVO;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.dto.UserVO;
import com.lalibrary.model.BookService;
import com.lalibrary.model.LibraryService;

@WebServlet("/auth/mypage.go")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookService bookService = new BookService();
		LibraryService libraryService = new LibraryService();
		UserVO loginInfo = (UserVO)session.getAttribute("userInfo");
		List<BookVO> bookList = bookService.selectByBook(loginInfo.getUser_id());
		
		//System.out.println("bookList"+bookList.size() );
		
		for(LibraryVO library: libraryService.selectLibraryAll()) {
			if( library.getLibrary_id().equals((String)loginInfo.getLibrary_id())) {
				request.setAttribute("libraryName", library.getLibrary_name());
			}
		}
		request.setAttribute("booklist", bookList);
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
