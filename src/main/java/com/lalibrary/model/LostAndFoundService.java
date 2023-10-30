package com.lalibrary.model;

import java.util.List;

import com.lalibrary.dto.LostAndFoundVO;

public class LostAndFoundService {
	LostAndFoundDAO dao = new LostAndFoundDAO();

	// 분실물 보관센터
	public List<LostAndFoundVO> selectLostAndFound(LostAndFoundVO lost) {
		return dao.selectLostAndFound(lost);
	}

	// 분실물 등록
	public int insertLostItem(LostAndFoundVO itemInfo) {
		return dao.insertLostItem(itemInfo);
	}

	// 분실물 수령
	public int deleteLostItem(LostAndFoundVO itemId) {
		return dao.deleteLostItem(itemId);
	}

}
