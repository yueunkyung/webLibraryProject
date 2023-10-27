package com.lalibrary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lalibrary.model.BookService;


@WebServlet("/welcomeLALA.go")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService();
		String libId = request.getParameter("library_id");
		String bookTitle = request.getParameter("book_title");
		
//		if(libId!=libId || bookTitle) {
//			
//		}
//		
		System.out.println("????????????????"+ libId+bookTitle);
		request.setAttribute("booklist", bookService.selectBookAll());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
