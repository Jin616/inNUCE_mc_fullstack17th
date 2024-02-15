package com.mc.innuce.domain.debate.dao;

import java.util.Collection;
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

	List<DebateRoomDTO> myDebateRoomList(List<Integer> subMyDebateRoomKeyList);

	List<DebateRoomDTO> selectDebateRoomListByKeyword(List<Integer> keywordKey);

	List<Integer> selectDebateRoomKeyListBySearchCount(int closeRoomSearchCount);

	int updateDebateRoomStatusOpen(List<Integer> mergedList);

	int updateDebateRoomStatusWarn(List<Integer> mergedList);

	int updateDebateRoomStatusClosed(List<Integer> mergedList);
}
