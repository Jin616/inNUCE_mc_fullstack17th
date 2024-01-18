package com.mc.innuce.domain.debate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.mc.innuce.domain.debate.dto.OpinionDTO;
import com.mc.innuce.domain.debate.service.OpinionService;

@Controller
public class OpinionController {
	@Autowired
	private OpinionService opinionService;
	
	@MessageMapping("/debate/{roomId}")
	@SendTo("/sub/debate/{roomId}")
	public OpinionDTO chat(@DestinationVariable int roomId, OpinionDTO opinion) {
		
		
		System.out.println(opinion.toString());
		// opinion에 저장 후 반환
		
		// 0118 수정해야할 내용
		// 어떤 유저가 쓴 글인지 표시 못하게 되기 때문에
		// message에 필요한 필드만 가진 message class 만들어 채팅에 사용하고
		// 채팅 전송 시 dto에 저장
		
		return opinion;
	}
}
