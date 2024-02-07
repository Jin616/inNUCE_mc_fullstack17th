package com.mc.innuce.domain.search.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.chatbot.ChatbotService;
import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeysDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.service.ComponentService;
import com.mc.innuce.domain.search.service.GeolocationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Controller
public class WordCloudController {


	@Autowired
	ComponentService service;

	@GetMapping("/wordCloud")
	public @ResponseBody List<String> wordCloud() throws IOException {

		String[] strArr = { "0", "1", "2", "3", "4", "5" };
		List<String> list = new ArrayList<>();
		
		for (String string : strArr) {
			
			HashMap<String, Integer> crawlerData = service.getCategoryContent(string);

			JSONArray jsonArray = new JSONArray();
			// 명사들을 하나씩
			for (String token : crawlerData.keySet()) {
				
				JSONObject informationObject = new JSONObject();

				// {"x": "token"}
				informationObject.put("x", token);
				// { value: 80 }
				informationObject.put("value", crawlerData.get(token));
				// {"x": "token", value: 80}
				jsonArray.put(informationObject);
			}

			list.add(jsonArray.toString());
		}

		return list;
	}

}
//jsonArray = {
//{"x": "token", value: 80},
//{"x": "token", value: 80},
//{"x": "token", value: 80},
//{"x": "token", value: 80}
//}
