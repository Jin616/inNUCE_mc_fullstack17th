package com.mc.innuce.domain.debate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.debate.dao.DebateRoomDAO;
import com.mc.innuce.domain.debate.dto.DebateRoomDTO;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.search.dao.SearchDAO;

@Service
public class DebateRoomService {
	@Autowired
	DebateRoomDAO debateRoomDAO;

	@Autowired
	SearchDAO searchDAO;

	@Autowired
	private NewsService newsService;

	private List<DebateRoomDTO> popularDebateRoom; // 인기 토론방 목록

	private static final int CREATE_ROOM_COUNT = 1; // 방 생성 검색 수 기준
	private static final int CLOSE_ROOM_SEARCH_COUNT = 1; // 최근 검색한 검색 수 기준
	private static final int CLOSE_ROOM_USER_COUNT = 1; // 최근 채팅한 유저 수 기준
	// 위 기준으로 채팅방 열고 닫기
	// 열린방은 목록에 표시 채팅 가능해짐
	// 닫을 예정인 방은 여러가지 방법으로 경고, 닫히는 기준 넘으면 닫히지 않는다고 알림
	// 닫힌방은 기존 들어갔던 사람은 접속 가능하나 채팅 불가능

	public List<DebateRoomDTO> openDebateRoomList(int num) {
		// TODO Auto-generated method stub
		return debateRoomDAO.openDebateRoomList(num);
	}

	public DebateRoomDTO selectOneDebateRoom(int debate_room_key) {
		// TODO Auto-generated method stub
		return debateRoomDAO.selectOneDebateRoom(debate_room_key);
	}

//	// 10분마다 방 열리고 닫힘 업데이트
//	@Scheduled(cron = "0 0/10 * * * *")
//	public void updateDebateRoomStatus() {
//		
//	}

	// 10분마다 방 생성 결정
	@Scheduled(cron = "0 0/10 * * * *")
	public void createDebateRoom() {
		// 방 생성 기준을 넘는 keyword_key list 가져오기
		// 24시간 이내 검색수가 CREATE_ROOM_COUNT이상인것
		List<Integer> passedKeywordList = searchDAO.selectPassedKeywordList(CREATE_ROOM_COUNT);
		System.out.println(passedKeywordList);
		// 기존 채팅방의 keyword_key list 가져오기
		List<Integer> existKeywordList = debateRoomDAO.selectExistKeywordList();
		System.out.println(existKeywordList);
		// 두 list 비교하여 기존 채팅방에 없는 keyword_key list 생성
		List<Integer> newKeywordList = passedKeywordList.stream()
				.filter(key -> !existKeywordList.contains(key))
				.collect(Collectors.toList());
		System.out.println(newKeywordList);
		// 기존 채팅방에 없는 keyword_key list로 채팅방 생성
		if (!newKeywordList.isEmpty()) {
			debateRoomDAO.createDebateRoom(newKeywordList);
		}
	}

	public int openDebateRoomCount() {
		// TODO Auto-generated method stub
		return debateRoomDAO.openDebateRoomCount();
	}

	public List<DebateRoomDTO> myDebateRoomList(List<Integer> subMyDebateRoomKeyList) {
		// TODO Auto-generated method stub
		return debateRoomDAO.myDebateRoomList(subMyDebateRoomKeyList);
	}

	@Scheduled(cron = "0 0/10 * * * *")
	public void updateChattingRoomList() {
		if (popularDebateRoom == null)
			popularDebateRoom = new ArrayList<>();

		if (!popularDebateRoom.isEmpty())
			popularDebateRoom.clear();

		List<Integer> keywordKey = newsService.selectTop4KeywordKey();
		popularDebateRoom.addAll(debateRoomDAO.selectDebateRoomListByKeyword(keywordKey));
	}

	public List<DebateRoomDTO> chattingRoomList() {
		// TODO Auto-generated method stub
		if(popularDebateRoom == null || popularDebateRoom.isEmpty()) {
			updateChattingRoomList();
		}
		System.out.println(popularDebateRoom);
		return popularDebateRoom;
	}

}
