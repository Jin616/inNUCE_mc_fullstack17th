package com.mc.innuce.domain.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.dto.SearchDTO;
import com.mc.innuce.domain.search.dto.KeysDTO;
import com.mc.innuce.domain.search.dto.KeysOfSearchDTO;

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
	public void insertKeywordNews(KeysDTO keysDTO) {
		keywordService.insertKeywordNews(keysDTO);
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
		// TODO Auto-generated method stub
		return newsService.selectOne(newsKey);
	}

	public int updateSearch(int keywordKey) {
		return searchService.updateSearch(keywordKey);
	}

	public int insertSearch(SearchDTO sDTO) {
		return searchService.insertSearch(sDTO);		
	}





}
