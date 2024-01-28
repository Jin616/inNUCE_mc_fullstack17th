package com.mc.innuce;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.geoloaction.GeolocationService;
import com.mc.innuce.domain.search.wordcloud.ParsingKomoran;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {
	@Autowired
	GeolocationService service;
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@GetMapping("/wordCloud")
	public @ResponseBody void wordCloud(@RequestParam String num, HttpServletResponse res, HttpServletRequest req)
			throws IOException {

		System.out.println(num);
		ParsingKomoran pk = new ParsingKomoran();
		HashMap<String, Integer> crawlerData = pk.parsingData(num);

//     	System.out.println("DataController(wordCloud): "+crawlerData.toString());
		JSONArray jsonArray = new JSONArray();
		// 명사들을 하나씩
		for (String token : crawlerData.keySet()) {
			JSONObject informationObject = new JSONObject();

//           {"x": "token"}
			informationObject.put("x", token);
//           { value: 80 }
			informationObject.put("value", crawlerData.get(token));
//						{"x": "token", value: 80}
			jsonArray.put(informationObject);
		}
//       jsonArray = {
//       		{"x": "token", value: 80},
//       		{"x": "token", value: 80},
//       		{"x": "token", value: 80},
//       		{"x": "token", value: 80}
//       }

		// printwriter 과 print 를 사용하여 값을 response 로 값을 전달함
		// pw 로 값을 전달하면 값이 response body 에 들어가서 보내짐
		res.setContentType("application/json;charset=utf-8");// 한글을 정상적으로 출력
		PrintWriter pw = res.getWriter();
		pw.print(jsonArray.toString());
		System.out.println(jsonArray);
//       	return jsonArray.toString();
	}
	
	

		@GetMapping("/search")
		public ModelAndView mainSearch(String keyword, HttpSession session) {
			ModelAndView mv = new ModelAndView();
			
			List<NewsDTO> newsList = new ArrayList<>();
			List<Long> newsKeyList = null; // News
//			KeywordDTO dto = compoService.oneKeyword(keyword);
			System.out.println("keyword :"+keyword);
			
//			if (keyword == null || keyword.isEmpty()) {
//				mv.setViewName("redirect:/main");
//			} else {
//				mv.setViewName("search/searchPage");
//			}
//			return mv;

//			코모란
//			dd

//			if (dto != null) {
//				// keyword_content가 있음
//				compoService.updateKeyword(dto);
//				newsKeyList = compoService.getNewsKeys2(keyword);
//			} else {
//				// keyword_content가 없음
//				compoService.insertKeyword(dto);
	//
//				// db거치지않고 news_key 들고오기
	//
//				// db거치고 news_key 들고오기
//				newsKeyList = compoService.getNewsKeys1();
//			}

//		news table 에서 검색어에 해당하는 news_key들을 insert
//		keyword_news table에 해당검색어와 여러 개의 news_key insert
//		insert into keyword_news(news_key,keyword_key) values( , );
			
//			KeyOfKeywordAndNewsDTO kkndto = new KeyOfKeywordAndNewsDTO(dto.getKeyword_key(), newsKeyList);
//			compoService.insertToKeywordNews(kkndto);
	//
//			newsList = compoService.getNewsList(dto);
//			// news_key -> news table news_key
//			mv.addObject("newsList", newsList);
			mv.addObject("keyword", keyword);
			mv.setViewName("search/searchPage");

			return mv;
		}

		// 검색 -> 네이버 뉴스 -> news 테이블에 해당하는 news_key가 여러 개
	//-> keyword_news에  news_key 등록
		
		
		
//		@RequestMapping("/myPlace")
//		String searchMyPlace() {
//			return "";
//			
//		}
	//	
		
		@RequestMapping("/myLocation")
		ModelAndView myLocation(HttpServletRequest request) throws UnknownHostException {
			ModelAndView mv = new ModelAndView();
			String ip = "";

			ip = request.getHeader("X-Forwarded-For");

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-RealIP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("REMOTE_ADDR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")){
	      InetAddress address = InetAddress.getLocalHost();
				ip = address.getHostAddress();

	  }
			System.out.println("ip : "+ip);
			String myLocation=service.test(ip);
			
			String r1="";
			String r2="";
			String r3="";
			try {
				JSONParser parser = new JSONParser();
				JSONObject result = (JSONObject)parser.parse(myLocation);
				JSONObject geoLocation = (JSONObject)result.get("geoLocation"); 
				
				r1 = (String)geoLocation.get("r1");
				r2 = (String)geoLocation.get("r2");
				r3 = (String)geoLocation.get("r3");
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			mv.addObject("myLocation", myLocation);
			mv.addObject("keyword", r1+" "+r2+" "+r3);
			mv.setViewName("search/searchPage");
			return mv;
		}
	
	
	
}
