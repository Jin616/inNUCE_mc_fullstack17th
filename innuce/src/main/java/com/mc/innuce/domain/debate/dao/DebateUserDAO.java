package com.mc.innuce.domain.debate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.debate.dto.DebateUserDTO;

@Repository
@Mapper
public interface DebateUserDAO {

	DebateUserDTO selectOneDebateUserByData(DebateUserDTO dudto);

	void insertDebateUser(DebateUserDTO dudto);

}
