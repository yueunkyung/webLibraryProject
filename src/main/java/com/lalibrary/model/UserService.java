package com.lalibrary.model;

import java.util.List;

import com.lalibrary.dto.UserVO;

public class UserService {
	UserDAO dao = new UserDAO();

	// 회원 전체 조회
	public List<UserVO> selectUser(String target, String sqlParam) {
		return dao.selectUser(target, sqlParam);
	}

	// 회원 가입
	public int insertSignUp(UserVO memberInfo) {
		return dao.insertSignUp(memberInfo);
	}

	//로그인
	public UserVO signinCheck(String id, int password) {
		return dao.signinCheck(id, password);
	}
}
