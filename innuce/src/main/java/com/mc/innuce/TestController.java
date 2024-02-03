package com.mc.innuce;

import java.io.IOException;
import java.io.PrintWriter;
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
public class TestController {
	@Autowired
	GeolocationService geoService;
	@Autowired
	ComponentService service;

	@RequestMapping("/main")
	public String main() {

		return "main";
	}

	@GetMapping("/wordCloud")
	public @ResponseBody void wordCloud(@RequestParam String num, HttpServletResponse res, HttpServletRequest req)
			throws IOException {

		System.out.println(num);
//		ParsingKomoran pk = new ParsingKomoran();
//		HashMap<String, Integer> crawlerData = pk.parsingDataWithSelenium(num);
		HashMap<String, Integer> crawlerData = service.getCategoryContent(num);

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

		int pageCount = 10;
		int totalCount = 0;
		int[] limit = new int[2];
		List<NewsDTO> newsList = new ArrayList<>();
		List<Integer> keywordKeyList = new ArrayList<>(); // News
//		List<KeywordDTO> keywords = new ArrayList<>();
		KeywordDTO dto = null;
//	keyword에 " "이 있을 때만 코모란을 돌리자

		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		String path = System.getProperty("user.dir");

		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");
		komoran.setUserDic(path + "/src/main/resources/static/dictionary/dic.user");

		KomoranResult komoranResult = komoran.analyze(keyword);

		List<String> analyzeList = komoranResult.getMorphesByTags("NNP", "NNG", "NNB");
		int keywordKey = 0;

		for (String token : analyzeList) {
			System.out.println("===" + token + "===");
			dto = service.oneKeyword(token);

			if (dto != null) {
				// token이 있으면 - update +
				service.updateKeyword(token);
				keywordKeyList.add(dto.getKeyword_key());
				keywordKey = dto.getKeyword_key();

			} else {
				// token이 없으면 - insert
				service.insertKeyword(token);

				dto = service.oneKeyword(token);
				keywordKeyList.add(dto.getKeyword_key());
				keywordKey = dto.getKeyword_key();

				System.out.println("news_key : " + service.getNewKeys(token));

				KeysDTO keys = new KeysDTO(keywordKey, service.getNewKeys(token));

				service.insertKeywordNews(keys);
				System.out.println("keyword_news에 keyword:[news_Key] insert완료");
			}
			totalCount += service.getTotalNews(keywordKey);
		} // for (String token : analyzeList)

//	paging

		Map<String, Object> map = new HashMap<>();

		limit[0] = (pageNum - 1) * pageCount;
		limit[1] = pageCount;

		map.put("keyword_key", keywordKeyList);

		map.put("num1", limit[0]);
		map.put("num2", limit[1]);
		System.out.println("totalCount : " + totalCount);

		if (totalCount >= 400) {
			totalCount = 400;
		}

		System.out.println("totalCount : " + totalCount);
		System.out.println("pageCount : " + pageCount);
		mv.addObject("totalCount", totalCount);
		mv.addObject("pageCount", pageCount);
//	키워드에 해당하는 news 가져오기
//		/////////////////////////////////////////////////////
		newsList = service.getNewsListLimit(map);

		mv.addObject("newsList", newsList);

		mv.addObject("keyword", keyword);
		mv.setViewName("search/searchPage");

		return mv;
	}

	@RequestMapping("/myLocation")
	ModelAndView myLocation(HttpServletRequest request,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) throws Exception {

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

		int pageCount = 10;
		int totalCount = 0;
		int[] limit = new int[2];
		List<Integer> keywordKeyList = new ArrayList<>();
		List<NewsDTO> newsList = new ArrayList<>();
//		List<KeywordDTO> keywords = new ArrayList<>();
		KeywordDTO dto = null;
		int keywordKey = 0;

		List<String> placeList = new ArrayList<>();
		placeList.add(r1);
		placeList.add(r2);

		for (String place : placeList) {

			if (place.equals("") || place.equals(" ") || place == null) {
				mv.addObject("placeMassage", "위치정보가 확인이 안됩니다.");
				mv.setViewName("main");
				return mv;
			}
			System.out.println("===" + place + "===");
			dto = service.oneKeyword(place);
			System.out.println("keywordDTO :" + dto);

			if (dto != null) {
				// place이 있으면 - update +
				service.updateKeyword(place);
				keywordKeyList.add(dto.getKeyword_key());
				keywordKey = dto.getKeyword_key();

			} else {
				// place이 없으면 - insert
				service.insertKeyword(place);

				dto = service.oneKeyword(place);
				keywordKeyList.add(dto.getKeyword_key());
				keywordKey = dto.getKeyword_key();
				// place 을 keyword_news에 insert + news에서 news_key를 같이
//				select keyword_key from keyword where keyword_content = '석열'; - int

				System.out.println("news_key : " + service.getNewKeys2(place));

				KeysDTO keys = new KeysDTO(keywordKey, service.getNewKeys2(place));

				service.insertKeywordNews(keys);
				System.out.println("keyword_news에 keyword:[news_Key] insert완료");
			}
			totalCount += service.getTotalNews(keywordKey);

		} // for (String place : analyzeList)

//	paging
		Map<String, Object> map = new HashMap<>();

		limit[0] = (pageNum - 1) * pageCount;
		limit[1] = pageCount;

		map.put("keyword_key", keywordKeyList);

		map.put("num1", limit[0]);
		map.put("num2", limit[1]);

		System.out.println("totalCount : " + totalCount);

		if (totalCount >= 400) {
			totalCount = 400;
		}

		System.out.println("totalCount : " + totalCount);
		System.out.println("pageCount : " + pageCount);
		mv.addObject("totalCount", totalCount);
		mv.addObject("pageCount", pageCount);
		newsList = service.getNewsListLimit(map);
//	키워드에 해당하는 news 가져오기
//		newsList = service.getNewsList(dto.getKeyword_key()).subList(limit[0], limit[1]);
		System.out.println("myLocation : " + myLocation);
		System.out.println("r1 r2 r3 : " + r1 + r2 + r3);

//		mv.addObject("myLocation", myLocation);
		mv.addObject("newsList", newsList);
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
