package com.mc.innuce.domain.debate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.debate.dao.DebateUserDAO;
import com.mc.innuce.domain.debate.dto.DebateRoomDTO;
import com.mc.innuce.domain.debate.dto.DebateUserDTO;

@Service
public class DebateUserService {
	@Autowired
	DebateRoomService debateRoomService;
	
	@Autowired
	DebateUserDAO debateUserDAO;

	// roomId 유효성 검사, DebateUser 테이블 중복 확인 후 결과 반환
	public int createDebateUser(DebateUserDTO dudto) {
		// TODO Auto-generated method stub
		int key = 0;
		// roomId 값으로 DebateRoom 데이터를 가져와 null이면 0 반환
		DebateRoomDTO tempdr = debateRoomService.selectOneDebateRoom(dudto.getDebate_room_key());
		if(tempdr == null) {
			return key;
		}
		// null이 아니면
		// DebateUser 테이블 검색하여
		// 기존 데이터(user_key, debate_room_key) 존재 하면 dto 반환
		DebateUserDTO tempdu = debateUserDAO.selectOneDebateUserByData(dudto);
		if(tempdu != null) {
			// debate_user_status가 0이면 1로 update
			if(tempdu.getDebate_user_status() == 0) {
				debateUserDAO.updateDebateUserStatusParticipated(tempdu.getDebate_user_key());
			}
		}
		// 기존 데이터 존재하지 않으면 생성 후 dto 반환
		else {
			// 기존 데이터 존재하지 않고 방이 닫혀 있으면 0 반환
			if(tempdr.getDebate_room_status() == 0) {
				return key;
			}
			debateUserDAO.insertDebateUser(dudto);
			tempdu = debateUserDAO.selectOneDebateUserByData(dudto);
		}
		
		// key값 저장
		key = tempdu.getDebate_user_key();
		
		return key;
	}
	
	// debate_user 테이블과 user 테이블을 통해 debate_user_key에 헤당하는 user의 id 반환
	public String selectOneUserId(int debate_user_key) {
		// TODO Auto-generated method stub
		return debateUserDAO.selectOneUserId(debate_user_key);
	}

	public List<String> selectUserIdList(List<Integer> debateUserKeyList) {
		// TODO Auto-generated method stub
		return debateUserDAO.selectUserIdList(debateUserKeyList);
	}

	public List<Integer> openDebateRoomUserCountList(List<Integer> openDebateRoomKeyList) {
		// TODO Auto-generated method stub
		return debateUserDAO.openDebateRoomUserCountList(openDebateRoomKeyList);
	}

	public int selectParticipatedUserCount(int roomId) {
		// TODO Auto-generated method stub
		return debateUserDAO.selectParticipatedUserCount(roomId);
	}

	public int updateDebateUserStatusLeave(int debate_user_key) {
		// TODO Auto-generated method stub
		return debateUserDAO.updateDebateUserStatusLeave(debate_user_key);
	}
	
}
