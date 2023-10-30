package com.lalibrary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lalibrary.dto.UserVO;
import com.lalibrary.model.UserService;

@WebServlet("/signinCheck.go")
public class signinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.isNew()) {
			//session.removeAttribute("signinResult");
			//session.removeAttribute("userInfo");
			session.invalidate();
		}
		request.getRequestDispatcher("signin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		int userPassword = Integer.parseInt(request.getParameter("user_password"));

		UserService userService = new UserService();
		UserVO user = userService.signinCheck(userId, userPassword);
		
		HttpSession session = request.getSession();
		
		if(user==null) {
			session.setAttribute("signinResult", "아이디와 비밀번호를 확인바랍니다.");
			response.sendRedirect("signinCheck.go");
			return;
		}
		
		session.setAttribute("signinResult", "");
		session.setAttribute("userInfo", user);
		
		response.sendRedirect("book/bookList.go");
	}

}
