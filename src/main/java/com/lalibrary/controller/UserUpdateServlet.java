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
import com.lalibrary.model.UserService;

@WebServlet("/auth/userUpdate.go")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//UserVO loginInfo = (UserVO)session.getAttribute("userInfo");
		LibraryService libraryService = new LibraryService();
		//System.out.println(loginInfo);
		request.setAttribute("librarylist", libraryService.selectLibraryAll());
		request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//UserVO loginInfo = (UserVO)session.getAttribute("userInfo");
		
		UserVO user = makeUser(request);
		UserService userService = new UserService();
		int result = userService.userUpdate(user);
		
		session.setAttribute("userUpdateResult", "");
		if(result == 0) {
			session.setAttribute("userUpdateResult", "회원 정보를 다시 확인하세요.");
			response.sendRedirect("userUpdate.go");
			return;
		}
		session.setAttribute("userInfo", user);
		response.sendRedirect("mypage.go");
	}

	private UserVO makeUser(HttpServletRequest request) {
		String userid = request.getParameter("user_id");
		int password = convertInteger(request.getParameter("password"));
		String username = request.getParameter("user_name");
		String libid = request.getParameter("library_id");
		String loc = request.getParameter("loc");
		String phone = request.getParameter("phone_no");

		UserVO user = new UserVO();
		user.setUser_id(userid);
		user.setPassword(password);
		user.setUser_name(username);
		user.setLibrary_id(libid);
		user.setLoc(loc);
		user.setPhone_no(phone);
		
		return user;
	}

	private int convertInteger(String str) {
		if (str == null)
			return 0;
		return Integer.parseInt(str);
	}
}
