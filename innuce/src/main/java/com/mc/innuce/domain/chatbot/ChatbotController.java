package com.mc.innuce.domain.chatbot;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.search.geoloaction.NaverService;

@Controller
public class ChatbotController {

	@Autowired
	ChatbotService chatbotService;
	
	@RequestMapping("/chatbotInput")
	ModelAndView input() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("chatbot/input");

		return mv;
	}

	@RequestMapping("/chatbotOutput")
	ModelAndView output(String request) throws IOException {

		String result = chatbotService.test(request);

		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);

		mv.setViewName("chatbot/output");

		return mv;
	
	}

	@RequestMapping("/main/chatbot")
	public String executeChatbot() {
		return "chatbot/chatbotAjaxInput";
	}

	@RequestMapping("/main/chatbotProcess")
	@ResponseBody
	public String executeChatbot1(String chotbot) {
		String result = chatbotService.test(chotbot);

		System.out.println("변경전=" + result);

		JSONObject json = new JSONObject(result);

		JSONArray bubbles = (JSONArray) json.get("bubbles");
		JSONObject bubble = (JSONObject) bubbles.get(0);
		bubble.remove("information");
		result = json.toString();

		return result;
	}

}
