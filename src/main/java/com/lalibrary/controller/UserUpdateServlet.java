package com.lalibrary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lalibrary.dto.UserVO;
import com.lalibrary.model.LibraryService;

@WebServlet("/auth/userUpdate.go")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO loginInfo = (UserVO)session.getAttribute("userInfo");
		LibraryService libraryService = new LibraryService();
		
		request.setAttribute("librarylist", libraryService.selectLibraryAll());
		request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
