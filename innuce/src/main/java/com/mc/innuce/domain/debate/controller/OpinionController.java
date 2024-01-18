package com.mc.innuce.domain.debate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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
		
		// opinion에 저장 후 반환
		
		// 0118 수정해야할 내용
		// 어떤 유저가 쓴 글인지 표시 못하게 되기 때문에
		// message에 필요한 필드만 가진 message class 만들어 채팅에 사용하고
		// 채팅 전송 시 dto에 저장
		
		// front 에서 back 으로 debate_user_key, opinion_contents만 보내면 되므로 opinionDTO 사용
		// back 에서 front 로 user_id, message, regdate, like 를 보내야 하므로 message 만들어 사용
		// opinion_key 는 채팅 하나를 식별하기 위해 message에 같이 보내준다 (hidden 요소로 숨김)
		
		// message class fields
		// String opinion_key, String user_id, String opinion_contents, Timestamp opinion_regdate, int opinion_like
		
		// opinion 저장 후 저장된 key 값 반환
		int opinion_key = opinionService.insertOpinion(opinion);
		
		// key 값으로 저장된 opinionDTO반환
		opinion = opinionService.selectOneOpinion(opinion_key);
		
		// debate_user_key로 user_id 찾기
		String user_id = debateUserService.selectOneUserId(opinion.getDebate_user_key());
		
		// message 객체 생성하여 정보 가져온 정보들 넣고 반환
		DebateMessage debateMessage = new DebateMessage(opinion.getOpinion_key()
															, user_id
															, opinion.getOpinion_contents()
															, opinion.getOpinion_regdate()
															, opinion.getOpinion_like());
		
		// 0119 수정해야 할 내용
		// regdate 형식 변환 진권님 했는지 질문
		// 이전 채팅 불러오는 기능
		// 좋아요 기능
		// 스케쥴러를 이용한 토론방 생성, 토론방 열고 닫기 기능
		
		return debateMessage;
	}
}
