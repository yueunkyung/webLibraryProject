package com.lalibrary.model;

import java.util.List;

//import com.lalibrary.dto.BorrowBookVO;
import com.lalibrary.dto.LibraryVO;

public class LibraryService {
	LibraryDAO dao = new LibraryDAO();

	//도서관 전체 조회
	public List<LibraryVO> selectLibraryAll(){
		return dao.selectLibraryAll();
	}
}
