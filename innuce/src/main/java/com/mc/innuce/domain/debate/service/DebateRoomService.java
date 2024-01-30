package com.mc.innuce.domain.debate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.debate.dao.DebateRoomDAO;
import com.mc.innuce.domain.debate.dto.DebateRoomDTO;
import com.mc.innuce.domain.search.dao.SearchDAO;

@Service
public class DebateRoomService {
	@Autowired
	DebateRoomDAO debateRoomDAO;
	
	@Autowired
	SearchDAO searchDAO;
	
	private static final int CREATE_ROOM_COUNT = 1; // 방 생성 검색 수 기준

	public List<DebateRoomDTO> openDebateRoomList() {
		// TODO Auto-generated method stub
		return debateRoomDAO.openDebateRoomList();
	}

	public DebateRoomDTO selectOneDebateRoom(int debate_room_key) {
		// TODO Auto-generated method stub
		return debateRoomDAO.selectOneDebateRoom(debate_room_key);
	}

	public int increaseParticipants(int debate_room_key) {
		// TODO Auto-generated method stub
		return debateRoomDAO.increaseParticipants(debate_room_key);
	}

	public int decreaseParticipants(int roomId) {
		// TODO Auto-generated method stub
		return debateRoomDAO.decreaseParticipants(roomId);
	}
	
//	// 10분마다 방 열리고 닫힘 업데이트
//	@Scheduled(cron = "0 0/10 * * * *")
//	public void updateDebateRoomStatus() {
//		
//	}
//	
//	// 10분마다 방 생성 결정
//	@Scheduled(cron = "0 0/10 * * * *")
//	public void createDebateRoom() {
//		// 방 생성 기준을 넘는 keyword_key list 가져오기
//		
//		// 기존 채팅방의 keyword_key list 가져오기
//		
//		// 두 list 비교하여 기존 채팅방에 없는 keyword_key list 생성
//		
//		// 기존 채팅방에 없는 keyword_key list로 채팅방 생성
//		
//	}
	
}
