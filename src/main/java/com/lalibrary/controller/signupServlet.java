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

@WebServlet("/auth/signup.go")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public signupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryService libraryService = new LibraryService();
		
		request.setAttribute("librarylist", libraryService.selectLibraryAll());
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = makeUser(request);
		UserService userService = new UserService();
		int result = userService.insertSignUp(user);
		
		if(result == 0) {
			session.setAttribute("signupResult", "회원 정보를 다시 확인하세요.");
			response.sendRedirect("signup.go");
			return;
		}

		session.setAttribute("signupResult", "");
		response.sendRedirect("../signinCheck.go");
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

	private double convertDouble(String str) {
		if (str == null) {
			return 0;
		}
		return Double.parseDouble(str);
	}

	private int convertInteger(String str) {
		if (str == null)
			return 0;
		return Integer.parseInt(str);
	}

}
