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
}
