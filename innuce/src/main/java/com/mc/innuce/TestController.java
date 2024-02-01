package com.mc.innuce;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.geoloaction.GeolocationService;
import com.mc.innuce.domain.search.service.ComponentService;
import com.mc.innuce.domain.search.wordcloud.ParsingKomoran;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Controller
public class TestController {
	@Autowired
	GeolocationService geoService;
	@Autowired
	ComponentService service;

	@RequestMapping("/main")
	public String main() {
		
		List<String> contentList =service.getNewsContent();
		
		
		
		
		
		
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
	public ModelAndView mainSearch(String keyword, HttpSession session,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		ModelAndView mv = new ModelAndView();

		List<NewsDTO> newsList = new ArrayList<>();
		List<Long> newsKeyList = new ArrayList<>(); // News
//		List<KeywordDTO> keywords = new ArrayList<>();
		List<Map<String, Object>> keysList = new ArrayList<>();
		KeywordDTO dto = null;
		System.out.println("keyword :" + keyword);
//	keyword에 " "이 있을 때만 코모란을 돌리자
		
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		String path = System.getProperty("user.dir");

		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");
		komoran.setUserDic(path + "/src/main/resources/static/dictionary/dic.user");

		KomoranResult komoranResult = komoran.analyze(keyword);

		List<String> analyzeList = komoranResult.getMorphesByTags("NNP", "NNG", "NNB");
		int keywordKey=0;
		
		for (String token : analyzeList) {
			System.out.println("===" + token + "===");
			dto = service.oneKeyword(token);
			System.out.println("keywordDTO :" + dto);
			
			if (dto != null) {
				// token이 있으면 - update +
				service.updateKeyword(token);
				System.out.println("keyword update완료");

			} else {
				// token이 없으면 - insert
				service.insertKeyword(token);
				System.out.println("keyword에 keyword insert완료");
				// token 을 keyword_news에 insert + news에서 news_key를 같이
//				select keyword_key from keyword where keyword_content = '석열'; - int
				dto = service.oneKeyword(token);
			}
			keywordKey = dto.getKeyword_key();
			System.out.println("keyword_key : " + keywordKey);
//				select news_key from news where news_title like '%석열%'; - list
			System.out.println(token + "에 해당하는 news_key : " + service.getNewKeys(token));
			
			for (Long l : service.getNewKeys(token)) {
				newsKeyList.add(l);
			}

		} // for (String token : analyzeList)
		System.out.println("newsKeyList : " + newsKeyList);
		

		for (Long newsKey : newsKeyList) {
			Map<String, Object> data = new HashMap<>();
			data.put("keyword_key", keywordKey);
			data.put("news_key", newsKey);
			keysList.add(data);
		}

		System.out.println("keyList : " + keysList);

		service.insertKeywordNews(keysList);
		System.out.println("keyword_news에 keyword:[news_Key] insert완료");


//	paging
	
		int pageCount = 10;
		int[] limit = new int[2];
		limit[0] = (pageNum - 1) * pageCount;
		limit[1] = limit[0]+pageCount;
		int totalCount = service.getTotalNews(keywordKey);
		
		int remain = totalCount % pageCount;
		int share = totalCount/pageCount;
		
		if(remain!=0 && pageNum==share+1) {
			limit[1] = limit[0]+remain;
		}
		System.out.println("totalCount : "+totalCount);
		
		if(totalCount >= 400) {
			totalCount=400;
		}
		
		System.out.println("totalCount : "+totalCount);
		System.out.println("pageCount : "+pageCount);
		mv.addObject("totalCount", totalCount);
		mv.addObject("pageCount",pageCount);
//	키워드에 해당하는 news 가져오기
		newsList = service.getNewsList(dto.getKeyword_key()).subList(limit[0], limit[1]);
		System.out.println("newsList 10개 : "+newsList);
		
		mv.addObject("newsList", newsList);
		
		mv.addObject("keyword", keyword);
		mv.setViewName("search/searchPage");

		return mv;
	}
	/*
	 * @GetMapping("/search") public ModelAndView mainSearch(String keyword,
	 * HttpSession session,
	 * 
	 * @RequestParam(value = "pageNum", required = false) int pageNum) {
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * 
	 * 
	 * return mv; }
	 */
	// 검색 -> 네이버 뉴스 -> news 테이블에 해당하는 news_key가 여러 개
	// -> keyword_news에 news_key 등록

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
		if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
			InetAddress address = InetAddress.getLocalHost();
			ip = address.getHostAddress();

		}
		System.out.println("ip : " + ip);
		String myLocation = geoService.test(ip);

		String r1 = "";
		String r2 = "";
		String r3 = "";
		try {
			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject) parser.parse(myLocation);
			JSONObject geoLocation = (JSONObject) result.get("geoLocation");

			r1 = (String) geoLocation.get("r1");
			r2 = (String) geoLocation.get("r2");
			r3 = (String) geoLocation.get("r3");

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("myLocation : " + myLocation);
		System.out.println("r1 r2 r3 : " + r1 + r2 + r3);
		mv.addObject("myLocation", myLocation);
		mv.addObject("keyword", r1 + " " + r2 + " " + r3);
		mv.setViewName("search/searchPage");
		return mv;
	}

	@RequestMapping("/news")
	public ModelAndView showNews() {
		ModelAndView mv = new ModelAndView();
//		해당 newsDTO 가져오기

		mv.setViewName("search/news");

		return mv;

	}

}
