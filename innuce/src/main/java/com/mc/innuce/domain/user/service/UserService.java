package com.mc.innuce.domain.user.service;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.print.attribute.HashAttributeSet;

import org.eclipse.angus.mail.util.logging.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.user.dao.UserDAO;
import com.mc.innuce.domain.user.dto.MailDTO;
import com.mc.innuce.domain.user.dto.UserDTO;


import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor 
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

		String phoneRegex = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})+$";
		String pwRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$";
		//겹치는 id를 가진 User가 있는 경우
		if(IdSelectedDTO != null) {
			return "아이디가 중복되었습니다";
		}
		// 핸드폰 양식이 다른경우
		else if(!Pattern.matches(pwRegex, dto.getUser_pw())){
			return "비밀번호 양식이 올바르지 않습니다";
		}
		// 핸드폰 양식이 다른경우
		else if(!Pattern.matches(phoneRegex, dto.getPhone())){
			return "전화번호 양식이 올바르지 않습니다";
		}
		

		// 정상 회원 가입 처리
		else {
			dao.insertUser(dto);
			return "정상 회원가입이 되었습니다";
		}
	}

	
	// 회원가입시 이메일 인증 기능
	
	
	// 회원정보 수정
	public void updateUser(UserDTO dto) {
		int user_key = dto.getUser_key();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", dto.getEmail());
		map.put("address", dto.getAddress());
		map.put("user_pw", dto.getUser_pw());
		map.put("phone", dto.getPhone());
		map.put("user_key", user_key);
		dao.updateUser(map);
	}

	// 회원탈퇴
	public void deleteuser(UserDTO dto) {
		int user_key = dto.getUser_key();

		boolean is_deleted = true;
		HashMap<String, Object> map = new HashMap<String , Object>();
		map.put("user_key",user_key);

		map.put("user_isdeleted", is_deleted);
		dao.deleteUser(map);
	}

	// 회원탈퇴 시간알기
	public String selectUserDeletedTime(UserDTO dto) {
		int user_key = dto.getUser_key();
		String user_deleted_time = dao.selectUserDeletedTime(user_key);
		return user_deleted_time;
	}

	// 회원 탈퇴 시간과 현재 시간 비교
	public boolean overTimeSinceDeleted(UserDTO dto) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime deleted_time = LocalDateTime.parse(dao.selectUserDeletedTime(dto.getUser_key()), formatter);
		System.out.println(deleted_time);
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);

		LocalTime start = deleted_time.toLocalTime();
		LocalTime end = now.toLocalTime();
		Duration diff = Duration.between(start, end);

		// 언제까지 복구 가능인지 기준
		int max_diff = 2;
		long diffMin = diff.toMinutes();
		System.out.println(diffMin);
		if (diffMin >= max_diff) {
			return true;
		} else {
			return false;
		}
	}

	// 회원 복구
	public void restoreUser(UserDTO dto) {
		int user_key = dto.getUser_key();
		dao.restoreUser(user_key);
		dto.setDeleted_time(null);
		dto.setUser_isdeleted(false);
	}

	// 아이디 찾기
	public String[] selectUserId(String user_name, String email) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_name", user_name);
		map.put("email", email);
		List<UserDTO> userDtoList = dao.selectUserId(map);

		String[] id_list = new String[userDtoList.size()];
		for (int i = 0; i < userDtoList.size(); i++) {
			id_list[i] = (userDtoList.get(i).getUser_id());
		}
		return id_list;
	}

	
	// 페이징 된 아이디 찾기
	public String[] selectPagingId(String user_name, String email, int starting_point, int user_in_page) {
		HashMap map = new HashMap();
		map.put("user_name", user_name);
		map.put("email", email);
		map.put("starting_point", starting_point);
		map.put("user_in_page",  user_in_page);
		
		List<UserDTO> userDtoList = dao.selectPagingUserDTO(map);
			
		String[] id_list = new String[userDtoList.size()];
		for(int i =0;  i < userDtoList.size() ; i++ ) {
			id_list[i] = (userDtoList.get(i).getUser_id());
		}
		return id_list;
	}
	
	
	// 페이징된 가입일자 찾기
	public String[] selectPagingRegdate(String user_name, String email, int starting_point, int user_in_page) {
		HashMap map = new HashMap();
		map.put("user_name", user_name);
		map.put("email", email);
		map.put("starting_point", starting_point);
		map.put("user_in_page",  user_in_page);
		
		List<UserDTO> userDtoList = dao.selectPagingUserDTO(map);
			
		String[] regdate_list = new String[userDtoList.size()];
		for(int i =0;  i < userDtoList.size() ; i++ ) {

			regdate_list[i] = (userDtoList.get(i).getRegdate());
		}
		return regdate_list;
	}

	
	// 관리자 화면에서 모든 페이징된 유저 담아오기
	public List<UserDTO> selectAllUserPagingList(int user_in_page, int starting_point) {
		HashMap map = new HashMap();
		map.put("user_in_page", user_in_page);
		map.put("starting_point", starting_point);
		
		List<UserDTO> userDtoList = dao.selectAllUserPagingList(map);
		System.out.println(userDtoList.size());
		return userDtoList;
	}
	
	// 관리자 화면에서 모든 유저 수 찾아오기
	public int selectAllUser() {
		int result = dao.selectAllUser();
		return result;
	}
	// 관리자 화면에서 아이디를 기준으로 유저 수 찾아오기 1 or 0 나올꺼임
	public int selectAllUserById(String user_id) {
		int result = dao.selectAllUserById(user_id);
		return result;
	}
	
	// 관리자 화면에서 아이디를 기준으로 페이징된  탈퇴 유저 담아오기
	public List<UserDTO> selectAllUserPagingListById(String user_id, int user_in_page, int starting_point) {
		HashMap map = new HashMap();
		map.put("user_in_page", user_in_page);
		map.put("starting_point", starting_point);
		map.put("user_id", user_id);
		List<UserDTO> userDtoList = dao.selectAllUserPagingListById(map);
		System.out.println(userDtoList.size());
		for(int i=0; i <userDtoList.size();i++) {
			System.out.println(userDtoList.get(0).getDeleted_time());
		}
		return userDtoList;
	}
	
	// 관리자 화면에서 이름을 기준으로 유저 수 찾아오기
	public int selectAllUserByUserName(String user_name) {
		int result = dao.selectAllUserByName(user_name);
		return result;
	}
	// 관리자 화면에서 이름을 기준으로 페이징된 유저 가져오기
	public List<UserDTO> selectAllUserPagingListByUserName(String user_name, int user_in_page, int starting_point) {
		HashMap map = new HashMap();
		map.put("user_in_page", user_in_page);
		map.put("starting_point", starting_point);
		map.put("user_name", user_name);
		List<UserDTO> userDtoList = dao.selectAllUserPagingListByUserName(map);
		System.out.println(userDtoList.size());
		for(int i=0; i <userDtoList.size();i++) {
			System.out.println(userDtoList.get(0).getDeleted_time());
		}
		return userDtoList;
	}
	// 관리자 화면에서 탈퇴 유저 수 찾아오기
	public int selectAllDeletedUser() {
		int result = dao.selectAllDeletedUser();
		return result;
	}
	// 관리자 화면에서 아이디를 기준으로 탈퇴 유저 수 찾아오기
	public int selectAllDeletedUserById(String user_id) {
		int result = dao.selectAllDeletedUserById(user_id);
		return result;
	}
	// 관리자 화면에서 이름을 기준으로 탈퇴 유저 수 찾아오기
	public int selectAllDeletedUserByUserName(String user_name) {
		// TODO Auto-generated method stub
		int result = dao.selectAllDeletedUserByName(user_name);
		return result;
	}

	// 관리자 화면에서 페이징된  탈퇴 유저 담아오기
	public List<UserDTO> selectAllDeletedUserPagingList(int user_in_page, int starting_point) {
		HashMap map = new HashMap();
		map.put("user_in_page", user_in_page);
		map.put("starting_point", starting_point);
		
		List<UserDTO> userDtoList = dao.selectAllDeletedUserPagingList(map);

		return userDtoList;
	}
	// 관리자 화면에서 아이디를 기준으로 탈퇴 유저 담아오기
	public List<UserDTO> selectAllDeletedUserPagingListById(String user_id, int user_in_page, int starting_point) {
		HashMap map = new HashMap();
		map.put("user_in_page", user_in_page);
		map.put("starting_point", starting_point);
		map.put("user_id", user_id);
		List<UserDTO> userDtoList = dao.selectAllDeletedUserPagingListById(map);

		return userDtoList;
	}

	public List<UserDTO> selectAllDeletedUserPagingListByUserName(String user_name, int user_in_page,
			int starting_point) {
		HashMap map = new HashMap();
		map.put("user_in_page", user_in_page);
		map.put("starting_point", starting_point);
		map.put("user_name", user_name);
		List<UserDTO> userDtoList = dao.selectAllDeletedUserPagingListByName(map);

		return userDtoList;
	}

	
	

}
