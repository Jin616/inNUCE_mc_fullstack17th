package com.mc.innuce.domain.debate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.debate.dto.DebateRoomDTO;

@Repository
@Mapper
public interface DebateRoomDAO {

	List<DebateRoomDTO> openDebateRoomList(int num);

	DebateRoomDTO selectOneDebateRoom(int debate_room_key);

	List<Integer> selectExistKeywordList();

	int createDebateRoom(List<Integer> newKeywordList);

	int openDebateRoomCount();
}
