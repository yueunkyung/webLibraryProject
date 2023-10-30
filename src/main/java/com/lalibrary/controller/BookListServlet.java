package com.lalibrary.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lalibrary.model.BookService;
import com.lalibrary.model.LibraryService;

@WebServlet("/book/bookList.go")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService();
		LibraryService libraryService = new LibraryService();		
		String libId = request.getParameter("library_id");
		String bookTitle = request.getParameter("book_name");

		//System.out.println(libId=="");
		//System.out.println(bookTitle=="");

		if(libId=="") libId = null;
		if(bookTitle=="") bookTitle = null;
		
		request.setAttribute("booklist", bookService.selectByBook(libId, bookTitle));
		
		request.setAttribute("librarylist", libraryService.selectLibraryAll());
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
