package com.mc.innuce.domain.debate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.debate.dto.DebateRoomDTO;

@Repository
@Mapper
public interface DebateRoomDAO {

	List<DebateRoomDTO> openDebateRoomList();

	DebateRoomDTO selectOneDebateRoom(int debate_room_key);

	int increaseParticipants(int debate_room_key);

	int decreaseParticipants(int roomId);

	List<Integer> selectExistKeywordList();

	int createDebateRoom(List<Integer> newKeywordList);
}
