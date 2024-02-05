package com.mc.innuce.domain.search.chatbot;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.search.geoloaction.NaverService;

@Controller
public class ChatbotController {

	@Autowired
	@Qualifier("chatbotService")
	NaverService service;
	
//	@Autowired
//	@Qualifier("ttsservice")
//	NaverService ttsService;

	@RequestMapping("/chatbotInput")
	ModelAndView input() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("chatbot/input");

		return mv;
	}

	@RequestMapping("/chatbotOutput")
	ModelAndView output(String request) throws IOException {

		String result = service.test(request);

		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);

		mv.setViewName("chatbot/output");

		return mv;
	}

//	@RequestMapping("/chatbotajaxstart")
//	String ajaxStart() {
//
//		return "chatbot/chatbotajax";
//	}

//	@RequestMapping("/chatbotajaxprocess")
//	@ResponseBody
//	String ajaxProcess(String request) {
//
//		String response = service.test(request);
//
//		return response;
//	}

//	@RequestMapping("/chatbottts")
//	@ResponseBody
//	String tts(String response) {
//		String mp3filename=ttsService.test(response);
//		
//		return "{\"mp3\": \""+mp3filename+"\"}";
//	}

}

/*
 * 
 * bubbles[0].type : text -> 기본답변 bubbles[0].data.cover.type : image -> 이미지답변
 * bubbles[0].data.cover.type : text -> 멀티링크답변
 * 
 */
