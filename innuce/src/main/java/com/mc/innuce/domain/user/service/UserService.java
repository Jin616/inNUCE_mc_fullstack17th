package com.mc.innuce.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.user.dao.UserDAO;
import com.mc.innuce.domain.user.dto.UserDTO;

@Service
public class UserService {
	
	@Autowired
	UserDAO dao;
	
	// 로그인 때 쓰임
	public UserDTO selectOneUser(String user_id) {
		
		return dao.selectOneUserByString(user_id);
	}
	
	// 회원가입 기능
	public String registerMember(UserDTO dto) {
	
		UserDTO selectedDTO = dao.selectOneUserByString(dto.getUser_id());
		String phone_regex = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
				
		//겹치는 id를 가진 User가 있는 경우
		if(selectedDTO != null) {
			return "아이디가 중복되었습니다";
		}
		
		// tel 형식 안맞는 경우
		else if(!dto.getEmail().matches(phone_regex)) {
			return "올바른 핸드폰 번호 형식을 입력해주세요";
		}
		// 정상 회원 가입 처리
		else {
			dao.insertUser(dto);
			return "정상 회원가입이 되었습니다";
		}
		
	}
	
	
	
	
}
