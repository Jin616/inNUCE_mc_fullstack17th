package com.mc.innuce.domain.user.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.user.dao.UserDAO;
import com.mc.innuce.domain.user.dto.UserDTO;



@Service
public class UserService {
	
	@Autowired
	UserDAO dao;
	
	// 로그인 때 쓰임 (스트링으로 user특정하기)
	public UserDTO selectOneUser(String user_id) {
		return dao.selectOneUserByString(user_id);
	}
	
	// 회원가입 기능
	public String registerMember(UserDTO dto) {
		UserDTO IdSelectedDTO = dao.selectOneUserByString(dto.getUser_id());
		
		//겹치는 id를 가진 User가 있는 경우
		if(IdSelectedDTO != null) {
			return "아이디가 중복되었습니다";
		}
		
		// 정상 회원 가입 처리
		else {
			dao.insertUser(dto);
			return "정상 회원가입이 되었습니다";
		}
	}
	
	// 회원정보 수정
	public void updateUser(UserDTO dto) {
		int user_key = dto.getUser_key();
		HashMap<String, Object> map = new HashMap<String , Object>();
		map.put("email", dto.getEmail());
		map.put("address", dto.getAddress());
		map.put("user_pw", dto.getUser_pw());
		map.put("phone", dto.getPhone());
		map.put("user_key",user_key);
		dao.updateUser(map);
	}
	// 회원탈퇴
	public void deleteuser(UserDTO dto) {
		int user_key = dto.getUser_key();
		boolean is_deleted = dto.isUser_isdeleted();
		HashMap<String, Object> map = new HashMap<String , Object>();
		map.put("user_key",user_key);
		map.put("user_isdeleted", is_deleted);
		dao.deleteUser(map);
	}
	
	//회원탈퇴 시간알기
	public String selectUserDeletedTime(UserDTO dto) {
		int user_key = dto.getUser_key();
		String user_deleted_time = dao.selectUserDeletedTime(user_key);
		return user_deleted_time;
	}
	
	// 회원 탈퇴 시간과 현재 시간 비교
	public boolean overTimeSinceDeleted(UserDTO dto) {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime deleted_time = LocalDateTime.parse(dao.selectUserDeletedTime(dto.getUser_key()),formatter);
		System.out.println(deleted_time);
		LocalDateTime now =  LocalDateTime.now();
		System.out.println(now);
		
		LocalTime start = deleted_time.toLocalTime();
		LocalTime end = now.toLocalTime();
		Duration diff = Duration.between(start, end);
		
		// 언제까지 복구 가능인지 기준
		int max_diff = 2;
		long diffMin = diff.toMinutes();
		System.out.println(diffMin);
		if(diffMin>=max_diff) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//회원 복구
	public void restoreUser(UserDTO dto) {
		int user_key = dto.getUser_key();
		dao.restoreUser(user_key);
		dto.setDeleted_time(null);
		dto.setUser_isdeleted(false);
	}
	
	// 아이디 찾기
	public String[] selectUserId(String user_name, String email) {
		HashMap<String, String> map = new HashMap<String , String>();
		map.put("user_name", user_name);
		map.put("email", email);
		List<UserDTO> userDtoList = dao.selectUserId(map);
		
		String[] id_list = new String[userDtoList.size()];
		for(int i =0;  i < userDtoList.size() ; i++ ) {
			id_list[i] = (userDtoList.get(i).getUser_id());
		}
	return id_list;
	}
	// 가입일자 찾기
		public String[] selectRegdate(String user_name, String email) {
			HashMap<String, String> map = new HashMap<String , String>();
			map.put("user_name", user_name);
			map.put("email", email);
			List<UserDTO> userDtoList = dao.selectUserId(map);
			
		
			String[] regdate_list = new String[userDtoList.size()];
			for(int i =0;  i < userDtoList.size() ; i++ ) {
				regdate_list[i] = (userDtoList.get(i).getRegdate());
			}
		return regdate_list;
		}
	
}
