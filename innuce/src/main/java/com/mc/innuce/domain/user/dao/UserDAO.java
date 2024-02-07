package com.mc.innuce.domain.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.user.dto.UserDTO;

@Repository
@Mapper
public interface UserDAO {

	UserDTO selectOneUserByInt(int user_key);

	// user_id 로 유저 특정 기능
	UserDTO selectOneUserByString(String user_id);

	List<UserDTO> selectUserList();

	// 회원가입 관련 기능
	int insertUser(UserDTO dto);

	// 회원 정보 수정 기능
	int updateUser(HashMap map);

	// 회원 삭제 기능
	int deleteUser(HashMap map);

	// 회원 탈퇴 시간 알기
	String selectUserDeletedTime(int user_key);

	// 회원 복구 기능
	int restoreUser(int user_key);

	// 아이디 찾기 기능
	List<UserDTO> selectUserId(HashMap<String, String> map);
	
	// 비밀번호 변경 기능
	int changePw (HashMap map);
	
	// 아이디와 이메일을 가지고 UserDTO를 페이징해서 가져오기
	List<UserDTO> selectPagingUserDTO(HashMap map);
	
	// 관리자 화면에서 UserDTO 수 가져오기
	int selectAllUser();
	
	// 관리자 화면에서 id 가지고 회원 숫자 찾기
	int selectAllUserById(String user_id);
	
	// 관리자 화면에서 이름 가지고 회원 숫자 찾기
	int selectAllUserByName(String user_name);
	
	// 관리자 화면에서 모든 유저를 페이징해서 가져오기
	List<UserDTO> selectAllUserPagingList(HashMap map);
	
	// 관리자 화면에서 id으로 페이징된 유저 가져오기
	List<UserDTO> selectAllUserPagingListById(HashMap map);
	
	// 관리자 화면에서 이름으로 페이징된 유저 가져오기
	List<UserDTO> selectAllUserPagingListByUserName(HashMap map);
		
	// 관리자 화면에서 탈퇴한 UserDTO 수
	int selectAllDeletedUser();
	
	// 관리자 화면에서 아이디를 기준으로 탈퇴 유저 수 가져오기
	int selectAllDeletedUserById(String user_id);
	
	// 관리자 화면에서 이름을 기준으로 탈퇴 유저 수 가져오기
	int selectAllDeletedUserByName(String user_name);

	// 관리자 화면에서 모든 탈퇴 유저들 페이징해서 가져오기
	List<UserDTO> selectAllDeletedUserPagingList(HashMap map);
	
	// 관리자 화면에서  아이디를 기준으로 탈퇴 유저들 페이징해서 가져오기
	List<UserDTO> selectAllDeletedUserPagingListById(HashMap map);

	// 관리자 화면에서  이름을 기준으로 탈퇴 유저들 페이징해서 가져오기
	List<UserDTO> selectAllDeletedUserPagingListByName(HashMap map);
	
	
	


	
	

	
}
