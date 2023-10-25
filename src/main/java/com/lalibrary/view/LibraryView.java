package com.lalibrary.view;

import java.util.List;

//import com.lalibrary.dto.BookVO;
//import com.lalibrary.dto.BorrowBookVO;
//import com.lalibrary.dto.LibraryVO;

public class LibraryView {
	public static void print(String message) {
		System.out.println("======================================================================================================================================================");
		System.out.println(message);
		System.out.println("======================================================================================================================================================");
		System.out.println();
	}

	public static void print(Object item) {
		System.out.println("======================================================================================================================================================");
		System.out.println(item);
		System.out.println("======================================================================================================================================================");
		System.out.println();
	}

	public static void print(List<?> itemlist) {
		System.out.println("======================================================================================================================================================");
		itemlist.stream().forEach(item -> {
			System.out.println(item);
		});
		System.out.println("======================================================================================================================================================");
		System.out.println();
	}
}
