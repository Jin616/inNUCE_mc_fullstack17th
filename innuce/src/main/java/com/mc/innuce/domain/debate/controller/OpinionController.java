package com.mc.innuce.domain.debate.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.innuce.domain.debate.dto.DebateMessage;
import com.mc.innuce.domain.debate.dto.OpinionDTO;
import com.mc.innuce.domain.debate.service.DebateUserService;
import com.mc.innuce.domain.debate.service.OpinionService;

@Controller
public class OpinionController {
	@Autowired
	private OpinionService opinionService;

	@Autowired
	private DebateUserService debateUserService;

	@MessageMapping("/debate/{roomId}")
	@SendTo("/sub/debate/{roomId}")
	public DebateMessage chat(@DestinationVariable int roomId, OpinionDTO opinion) {

		// front 에서 back 으로 debate_user_key, opinion_contents만 보내면 되므로 opinionDTO 사용
		// back 에서 front 로 user_id, message, regdate, like 를 보내야 하므로 message 만들어 사용
		// opinion_key 는 채팅 하나를 식별하기 위해 message에 같이 보내준다 (hidden 요소로 숨김)

		// message class fields
		// String opinion_key, String user_id, String opinion_contents, Timestamp
		// opinion_regdate, int opinion_like

		// opinion 저장 후 저장된 key 값 반환
		int opinion_key = opinionService.insertOpinion(opinion);

		// key 값으로 저장된 opinionDTO반환
		opinion = opinionService.selectOneOpinion(opinion_key);

		// debate_user_key로 user_id 찾기
		String user_id = debateUserService.selectOneUserId(opinion.getDebate_user_key());

		// message 객체 생성하여 가져온 정보들 넣고 반환
		DebateMessage debateMessage = new DebateMessage(opinion.getOpinion_key(), user_id,
				opinion.getOpinion_contents(), opinion.getOpinion_regdate(), opinion.getOpinion_like());

		return debateMessage;
	}

	// 이전 채팅 불러오기 ajax post 요청
	@PostMapping("/debate/loadmessage/{roomId}")
	public @ResponseBody List<DebateMessage> loadMessage(@PathVariable int roomId, int opinion_key) {
		// opinion_key로 찾은 opinion list 저장할 객체
		List<OpinionDTO> opinionList = null;

		// 채팅이 전혀 없는 경우 (처음페이지를 열었을 때)
		if (opinion_key == 0) {
			// roomId로 헤당 방의 가장 최근 insert한 opinion_key값을 가져옴
			opinion_key = opinionService.selectLastOpinion(roomId);
			
			if(opinion_key != 0) {
				// 채팅 가져오기
				opinionList = opinionService.selectPreOpinionList(opinion_key);
			}
		} else {
			// 받아온 opinion_key는 이미 페이지에 존재하기 때문에 하나 이전의 key값부터 가져옴
			opinionList = opinionService.selectOnePreOpinionList(opinion_key);
		}

		// 만약 이전 채팅이 없다면 이전 채팅이 없다는 메시지 반환
		if (opinionList == null || opinionList.isEmpty()) {
			List<DebateMessage> emptyMessageList = new ArrayList<DebateMessage>();
			emptyMessageList.add(new DebateMessage(opinion_key, "system", "이전 메시지가 없습니다.",
					new Timestamp(System.currentTimeMillis()), 0));
			return emptyMessageList;
		}

		// opinion list를 debate message list로 변환
		// debate_user_key list로 user_id list 찾기
		List<Integer> debateUserKeyList = new ArrayList<Integer>();

		opinionList.forEach((opinion) -> {
			debateUserKeyList.add(opinion.getDebate_user_key());
		});

		List<String> userIdList = debateUserService.selectUserIdList(debateUserKeyList);

		// message list 객체 생성하여 가져온 정보들 넣고 반환
		List<DebateMessage> debateMessageList = new ArrayList<DebateMessage>();

		for (int i = 0; i < opinionList.size(); i++) {
			debateMessageList.add(new DebateMessage(opinionList.get(i).getOpinion_key(), userIdList.get(i),
					opinionList.get(i).getOpinion_contents(), opinionList.get(i).getOpinion_regdate(),
					opinionList.get(i).getOpinion_like()));
		}

		System.out.println(debateMessageList.toString());

		return debateMessageList;
	}

	// 채팅방 연결시 메시지
	@MessageMapping("/debate/connected/{roomId}")
	@SendTo("/sub/debate/participants/{roomId}")
	public String connectMessage(@DestinationVariable int roomId, String message) {
		int debate_user_key = parseDebateUserKey(message);
		
		// debate_user_connect_status 1으로 변경
		debateUserService.updateDebateUserConnectStatusConnect(debate_user_key);

		// 채팅방 유저 수 정보 반환
		return debateRoomUserCount(roomId);
	}

	// 채팅방 연결 해제시 메시지
	@MessageMapping("/debate/disconnected/{roomId}")
	@SendTo("/sub/debate/participants/{roomId}")
	public String disconnectMessage(@DestinationVariable int roomId, String message) {
		int debate_user_key = parseDebateUserKey(message);
		
		// debate_user_connect_status 0으로 변경
		debateUserService.updateDebateUserConnectStatusDisconnect(debate_user_key);

		// 채팅방 유저 수 정보 반환
		return debateRoomUserCount(roomId);
	}

	// 채팅방 나가기 메시지
	@MessageMapping("/debate/leaveroom/{roomId}")
	@SendTo("/sub/debate/participants/{roomId}")
	public String leaveRoomMessage(@DestinationVariable int roomId, String message) {
		int debate_user_key = parseDebateUserKey(message);
		
		// debate_user_status 0으로 변경
		debateUserService.updateDebateUserStatusLeave(debate_user_key);

		// 채팅방 유저 수 정보 반환
		return debateRoomUserCount(roomId);
	}

	private int parseDebateUserKey(String message) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(message);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long keystr = (Long) jsonObject.get("message");
		int debate_user_key = keystr.intValue();
		return debate_user_key;
	}

	private String debateRoomUserCount(int roomId) {
		// 채팅방 participants_num 가져오기
		int connected = debateUserService.selectConnectedUserCount(roomId);
		// 채팅방 참여자 수를 debate_user 테이블의 해당 debate_room_key이고 status가 1인 debate_user count
		// 가져오기
		int participated = debateUserService.selectParticipatedUserCount(roomId);

		return "{\"connected\" : \"" + connected + "\", \"participated\" : \"" + participated + "\"}";
	}

}
