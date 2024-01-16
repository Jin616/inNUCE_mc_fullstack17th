package com.mc.innuce.domain.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.user.dto.UserDTO;

@Repository
@Mapper
public interface UserDAO {
	
	UserDTO selectOneUserByInt(int user_key) ;
	
	UserDTO selectOneUserByString(String user_id) ;
	
	List<UserDTO> selectUserList();
	
	int updateUser(int user_key) ;
	
	int deleteUser(int user_Key);
	
	// 회원가입 관련 기능
	int insertUser(UserDTO dto);
}
