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
	
	@MessageMapping("/{roomId}")
	@SendTo("/room/{roomId}")
	public OpinionDTO chat(@DestinationVariable int roomId, OpinionDTO opinion) {
		
		// opinion에 저장 후 반환
		
		return opinion;
	}
}
