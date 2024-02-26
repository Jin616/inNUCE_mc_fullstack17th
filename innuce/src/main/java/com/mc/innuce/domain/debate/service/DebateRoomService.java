package com.mc.innuce.domain.debate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.debate.dao.DebateRoomDAO;
import com.mc.innuce.domain.debate.dao.DebateUserDAO;
import com.mc.innuce.domain.debate.dto.DebateRoomDTO;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.search.dao.SearchDAO;

@Service
public class DebateRoomService {
	@Autowired
	private DebateRoomDAO debateRoomDAO;

	@Autowired
	private SearchDAO searchDAO;

	@Autowired
	private DebateUserDAO debateUserDAO;
	
	@Autowired
	private NewsService newsService;

	private List<DebateRoomDTO> popularDebateRoom; // 인기 토론방 목록

	private static final int CREATE_ROOM_COUNT = 1; // 방 생성 검색 수 기준
	private static final int CLOSE_ROOM_SEARCH_COUNT = 1; // 최근 검색한 검색 수 기준
	private static final int CLOSE_ROOM_USER_COUNT = 1; // 최근 채팅한 유저 수 기준
	// 위 기준으로 채팅방 열고 닫기
	// 열린방은 목록에 표시, 채팅 가능해짐
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

	// 10분마다 방 열리고 닫힘 업데이트
	@Scheduled(cron = "0 0/10 * * * *")
	public void updateDebateRoomStatus() {
		// 검색 기준 넘은 debate_key list
		List<Integer> searchDebateRoomKeyList = debateRoomDAO.selectDebateRoomKeyListBySearchCount(CLOSE_ROOM_SEARCH_COUNT);
		// 최근 채팅 유저수 기준 넘은 debate_key list
		List<Integer> userDebateRoomKeyList = debateUserDAO.selectDebateRoomKeyListByUserCount(CLOSE_ROOM_USER_COUNT);
		// 검색 기준을 넘거나 또는 최근 채팅 유저 수 기준 넘은 debate_key list
		List<Integer> mergedList = new ArrayList<>(searchDebateRoomKeyList);
		mergedList.removeAll(userDebateRoomKeyList);
		mergedList.addAll(userDebateRoomKeyList);
		
		// 합쳐진 리스트에 해당하고 닫혔거나 닫힐 예정인 채팅방 열기
		debateRoomDAO.updateDebateRoomStatusOpen(mergedList);
		// 합쳐진 리스트에 해당하지 않고 열려있는 채팅방은 닫힐 예정으로 닫힐 예정인 채팅방은 닫기
		// 닫을거 먼저 닫고
		debateRoomDAO.updateDebateRoomStatusClosed(mergedList);
		// 다음 경고
		debateRoomDAO.updateDebateRoomStatusWarn(mergedList);
	}

	// 10분마다 방 생성 결정
	@Scheduled(cron = "0 0/10 * * * *")
	public void createDebateRoom() {
		// 방 생성 기준을 넘는 keyword_key list 가져오기
		// 24시간 이내 검색수가 CREATE_ROOM_COUNT이상인것
		List<Integer> passedKeywordList = searchDAO.selectPassedKeywordList(CREATE_ROOM_COUNT);
		
		// 기존 채팅방의 keyword_key list 가져오기
		List<Integer> existKeywordList = debateRoomDAO.selectExistKeywordList();
		
		// 두 list 비교하여 기존 채팅방에 없는 keyword_key list 생성
		List<Integer> newKeywordList = passedKeywordList.stream()
				.filter(key -> !existKeywordList.contains(key))
				.collect(Collectors.toList());
		
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
