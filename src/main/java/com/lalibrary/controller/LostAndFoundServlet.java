package com.lalibrary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lalibrary.dto.LostAndFoundVO;
import com.lalibrary.model.LibraryService;
import com.lalibrary.model.LostAndFoundService;

@WebServlet("/lostAndFound/lostAndFound.go")
public class LostAndFoundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LostAndFoundVO lost = new LostAndFoundVO();
		LibraryService libraryService = new LibraryService();
		LostAndFoundService lostService = new LostAndFoundService();	
		String libId = request.getParameter("library_id");
		String propertyName = request.getParameter("property_name");

		//빈 값 null 처리
		if(libId=="") libId = null;
		if(propertyName=="") propertyName = null;

		if(libId!="" && propertyName!="") {
			lost.setProperty_name(propertyName);
			lost.setLibrary_id(libId);
		} else if(libId!="") {
			lost.setProperty_name(propertyName);
		} else if(propertyName!="") {
			lost.setLibrary_id(libId);
		} 
		
		request.setAttribute("lostlist", lostService.selectLostAndFound(lost));
		request.setAttribute("librarylist", libraryService.selectLibraryAll());
		request.getRequestDispatcher("lostAndFoundList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
