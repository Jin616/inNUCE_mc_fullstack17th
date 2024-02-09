package com.mc.innuce.domain.search.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeysDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.dto.SearchDTO;
import com.mc.innuce.domain.search.service.ComponentService;
import com.mc.innuce.domain.search.service.GeolocationService;
import com.mc.innuce.domain.user.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Controller
public class SearchController {

	private Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

	@Autowired
	private GeolocationService geoService;
	@Autowired
	private ComponentService service;

	private String ip = "";
	private String agent = "";

	final int PAGECOUNT = 10;


	@RequestMapping("/main")
	public String main(HttpServletRequest request, HttpSession session) {

		return "main";
	}

	@GetMapping("/search")
	public ModelAndView mainSearch(String keyword, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) throws Exception {

		if (ip.equals("") || ip.equals(" ") || ip == null) {
			ip = getIp(request);
		}

		System.out.println("기기: " + ip);

		ModelAndView mv = new ModelAndView();

		int totalCount = 0;
		int keywordKey = 0;
		int[] limit = new int[2];
		List<NewsDTO> newsList = new ArrayList<>();
		List<Integer> keywordKeyList = new ArrayList<>();
		
		KeywordDTO kDTO = null;
		UserDTO uDTO = null;
		SearchDTO sDTO = null;

//		if (keyword.isEmpty() || keyword.isBlank()) {
//			mv.addObject("keyword", "\"" + keyword + "\"" + "에 대한 검색결과가 없습니다.");
//			mv.addObject("newsList", newsList);
//			mv.addObject("totalCount", totalCount);
//			mv.addObject("pageCount", PAGECOUNT);
//
//			mv.setViewName("search/searchPage");
//			return mv;
//		}

//	keyword에 " "이 있을 때만 코모란을 돌리자? x
		String path = System.getProperty("user.dir");

		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");

		KomoranResult komoranResult = komoran.analyze(keyword);

//		일반명사NNG
//		고유명사NNP
		List<String> analyzeList = komoranResult.getMorphesByTags("NNP", "NNG");

//		코모란이 인식x : 영단어 
		if (analyzeList.isEmpty()) {
			analyzeList.add(keyword);
		}

		for (String token : analyzeList) {
			List<Long> newsKeyList = service.getNewsKeys(token);

			if (token.length() >= 2) {

				if (newsKeyList.isEmpty() || newsKeyList == null) {
					mv.addObject("noneKeyword", "\"" + keyword + "\"" + "에 대한 검색결과가 없습니다.");
					mv.addObject("totalCount", totalCount);
					mv.addObject("pageCount", PAGECOUNT);
					mv.addObject("newsList", newsList);

					mv.setViewName("search/searchPage");

					return mv;
				} else {

					System.out.println("===" + token + "===");
					kDTO = service.oneKeyword(token);
					// keyword | keyword_news 테이블
					if (kDTO != null) {
						// token이 있으면
						// Keyword 테이블
						service.updateKeyword(token);
						System.out.println("updateKeyword 완료");
						keywordKeyList.add(kDTO.getKeyword_key());
						keywordKey = kDTO.getKeyword_key();

					} else {
						// token이 없으면 - insert
						// Keyword 테이블
						service.insertKeyword(token);
						System.out.println("insertKeyword 완료");
						kDTO = service.oneKeyword(token);
						keywordKeyList.add(kDTO.getKeyword_key());
						keywordKey = kDTO.getKeyword_key();

						KeysDTO keys = new KeysDTO(keywordKey, newsKeyList);

						service.insertKeywordNews(keys);
						System.out.println("insertKeywordNews 완료");

					}
					System.out.println("keyword_key : " + keywordKey);
					// Search 테이블 - 유저에 따라 insert | update
					if (session.getAttribute("login_user") != null) {
						// userDTO가 존재
						uDTO = (UserDTO) session.getAttribute("login_user");
						// keyword_key / userKey / ip / age / gender 를 search 에 저장.
						sDTO = new SearchDTO(keywordKey, uDTO.getUser_key(), ip);

						SearchDTO oneSearchDTO = service.oneSearch(sDTO);
						System.out.println("oneSearch 완료" + oneSearchDTO);
						if (oneSearchDTO != null) {
							int i = service.updateSearch2(sDTO);
							System.out.println(i + "userDTO가 존재o k-u-c 존재o updateSearch완료");
						} else {
							int i = service.insertSearch(sDTO);
							System.out.println(i + "userDTO가 존재o k-u-c 존재x insertSearch완료");
						}

					} else {
						// userDTO가 존재 x
						sDTO = new SearchDTO(keywordKey, ip);

						SearchDTO oneSearchDTO = service.oneSearch2(sDTO);
						System.out.println("oneSearch2 완료" + oneSearchDTO);
						if (oneSearchDTO != null) {
							// ip == clinet_key 가 존재
							int i = service.updateSearch(sDTO);
							System.out.println(i + "userDTO가 존재x k-c 존재o updateSearch완료");
						} else {
							// client_key 존재 x
							int i = service.insertSearch2(sDTO);
							System.out.println(i + "userDTO가 존재x k-c 존재x insertSearch2완료");
						}
					}

					totalCount += service.getTotalNews(keywordKey);
				}
			}
		} // for (String token : analyzeList)

		if (keywordKeyList.isEmpty()) {
			System.out.println("keywordKeyList 가 비어있습니다.");
			mv.addObject("noneKeyword", "\"" + keyword + "\"" + "에 대한 검색결과가 없습니다.");
		} else {
//	paging
			Map<String, Object> map = new HashMap<>();

			limit[0] = (pageNum - 1) * PAGECOUNT;
			limit[1] = PAGECOUNT;

			map.put("keyword_key", keywordKeyList);

			map.put("num1", limit[0]);
			map.put("num2", limit[1]);
			System.out.println("totalCount : " + totalCount);

			if (totalCount >= 100) {
				totalCount = 100;
			}

//	키워드에 해당하는 news 가져오기
			newsList = service.getNewsListLimit(map);

			mv.addObject("newsList", newsList);
			mv.addObject("keyword", keyword);
		}

		System.out.println("totalCount : " + totalCount);
		System.out.println("pageCount : " + PAGECOUNT);
		mv.addObject("totalCount", totalCount);
		mv.addObject("pageCount", PAGECOUNT);
		mv.setViewName("search/searchPage");

		return mv;

	}

	private String getDevice(HttpServletRequest request) {
		agent = request.getHeader("USER-AGENT");
		return agent;
	}

	@RequestMapping("/myLocation")
	ModelAndView myLocation(HttpServletRequest request,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) throws Exception {

		ModelAndView mv = new ModelAndView();

		if (ip.equals("") || ip.equals(" ") || ip == null) {
			ip = getIp(request);
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

		int totalCount = 0;
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

				System.out.println("news_key : " + service.getNewsKeys2(place));

				KeysDTO keys = new KeysDTO(keywordKey, service.getNewsKeys2(place));

				service.insertKeywordNews(keys);
			}
			totalCount += service.getTotalNews(keywordKey);

		} // for (String place : analyzeList)

//	paging
		Map<String, Object> map = new HashMap<>();

		int[] limit = new int[2];

		limit[0] = (pageNum - 1) * PAGECOUNT;
		limit[1] = PAGECOUNT;

		map.put("keyword_key", keywordKeyList);

		map.put("num1", limit[0]);
		map.put("num2", limit[1]);

		if (totalCount >= 100) {
			totalCount = 100;
		}

		System.out.println("totalCount : " + totalCount);
		System.out.println("pageCount : " + PAGECOUNT);
		mv.addObject("totalCount", totalCount);
		mv.addObject("pageCount", PAGECOUNT);
//	키워드에 해당하는 news 가져오기

		if (newsList.isEmpty()) {
			newsList = service.getNewsListLimit(map);
		}
		
		if (newsList.isEmpty()) {
			mv.addObject("noneKeyword", "\"" + r1 + " " + r2 + " " + r3 + "\"" + "에 대한 검색결과가 없습니다.");
		} else {
			mv.addObject("keyword", r1 + " " + r2 + " " + r3);
		}

		mv.addObject("newsList", newsList);

		mv.setViewName("search/searchPage");
		return mv;

	}

	private String getIp(HttpServletRequest request) throws UnknownHostException {

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
		return ip;
	}

	@RequestMapping("/news")
	public ModelAndView showNews(String newsKey) {
		ModelAndView mv = new ModelAndView();
//		해당 newsDTO 가져오기
		NewsDTO dto = service.oneNews(newsKey);

		mv.addObject("News", dto);
		System.out.println(dto);
		mv.setViewName("search/news");

		return mv;

	}

}
