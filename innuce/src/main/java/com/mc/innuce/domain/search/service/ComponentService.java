package com.mc.innuce.domain.search.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.search.dto.KeysDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.dto.SearchDTO;
import com.mc.innuce.global.util.sqltojava.SqlConverter;

@Service
public class ComponentService {

	@Autowired
	KeywordService keywordService;
	@Autowired
	SearchService searchService;
	@Autowired
	NewsService newsService;
	@Autowired
	WordCloudService cloudService;
	@Autowired
	GeolocationService geoService;
	@Autowired
	SqlConverter sc;
	
	public KeywordDTO oneKeyword(String keyword) {
		return keywordService.oneKeyword(keyword);
	}

	public int updateKeyword(String keyword) {
		return keywordService.updateKeyword(keyword);
	}

	public int insertKeyword(String keyword) {
		return keywordService.insertKeyword(keyword);
	}

//  키워드가 없을때 news_key가져오기
	public List<Long> getNewsKeys(String keyword) {
		return keywordService.getNewsKeys(keyword);
	}

//	위치 검색 시 news_key
	public List<Long> getNewsKeys2(String place) {
		return keywordService.getNewsKeys2(place);
	}

//	키워드가 있을때 news_key가져오기
//	public List<Long> getNewsKeys2(String keyword) {
//		return keywordService.getNewsKeys2(keyword);
//	}
	public int insertKeywordNews(KeysDTO keysDTO) {
		return keywordService.insertKeywordNews(keysDTO);
	}

	public List<NewsDTO> getNewsListLimit(Map<String, Object> map) {
		return keywordService.getNewsListLimit(map);
	}

	public int getTotalNews(int keyword_key) {
		return keywordService.getTotalNews(keyword_key);
	}

//	뉴스 내용들 모두 들고오기
	public List<String> getNewsContent() {
		return searchService.getNewsContent();
	}

	public HashMap<String, Integer> getCategoryContent(String category) {
		return cloudService.getCategoryContent(category);
	}

	public NewsDTO oneNews(String newsKey) {
		return newsService.selectOne(newsKey);
	}

	public int updateSearch(SearchDTO sDTO) {
		return searchService.updateSearch(sDTO);
	}

	public int updateSearch2(SearchDTO sDTO) {
		return searchService.updateSearch2(sDTO);
	}

	public int insertSearch(SearchDTO sDTO) {
		return searchService.insertSearch(sDTO);
	}

	public int insertSearch2(SearchDTO sDTO) {
		return searchService.insertSearch2(sDTO);
	}

	public int deleteKeyword(String token) {
		return keywordService.deleteKeyword(token);
	}

	public SearchDTO oneSearch(SearchDTO sDTO) {
		return searchService.oneSearch(sDTO);
	}

	public SearchDTO oneSearch2(SearchDTO sDTO) {
		return searchService.oneSearch2(sDTO);
	}

	// seo start
	public int getTotalNewsOptionPeriod(int keywordKey, String ds, String de) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("keywordKey", String.valueOf(keywordKey));
		map.put("ds", sc.localDateTimeToTimestamp(LocalDate.parse(ds).atTime(0, 0, 0)));
		map.put("de", sc.localDateTimeToTimestamp(LocalDate.parse(de).atTime(23, 59, 59)));
		
		System.out.println("ds" + map.get("ds"));
		System.out.println("de" + map.get("de"));
		return keywordService.getTotalNewsOptionPeriod(map);
	}

	public int getTotalNewsOptionPress(int keywordKey, List<Integer> pressKeyList) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("keywordKey", keywordKey);
		map.put("pressKeyList", pressKeyList);
		
		return keywordService.getTotalNewsOptionPress(map);
	}

	public int getTotalNewsOptionPeriodPress(int keywordKey, String ds, String de, List<Integer> pressKeyList) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("keywordKey", keywordKey);
		map.put("pressKeyList", pressKeyList);
		map.put("ds", sc.localDateTimeToTimestamp(LocalDate.parse(ds).atTime(0, 0, 0)));
		map.put("de", sc.localDateTimeToTimestamp(LocalDate.parse(de).atTime(23, 59, 59)));
		
		return keywordService.getTotalNewsOptionPeriodPress(map);
	}
	
	public List<NewsDTO> getNewsListLimitOptionPeriod(Map<String, Object> map) {
		return keywordService.getNewsListLimitOptionPeriod(map);
	}

	public List<NewsDTO> getNewsListLimitOptionPress(Map<String, Object> map) {
		return keywordService.getNewsListLimitOptionPress(map);
	}
	
	public List<NewsDTO> getNewsListLimitOptionPressPeriod(Map<String, Object> map) {
		return keywordService.getNewsListLimitOptionPressPeriod(map);
	}
	// seo end

}