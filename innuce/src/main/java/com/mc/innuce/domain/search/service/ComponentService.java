package com.mc.innuce.domain.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Service
public class ComponentService {

	@Autowired
	KeywordService keywordService;
	@Autowired
	SearchService searchService;
	@Autowired
	CRUDService crudService;
	@Autowired
	NewsService newsService;

	public KeywordDTO oneKeyword(String keyword) {
		return crudService.oneKeyword(keyword);
	}

	public int updateKeyword(String keyword) {
		return crudService.updateKeyword(keyword);
	}

	public int insertKeyword(String keyword) {
		return crudService.insertKeyword(keyword);
	}

//  키워드가 없을때 news_key가져오기
	public List<Long> getNewKeys(String keyword) {
		return keywordService.getNewKeys(keyword);
	}

//	키워드가 있을때 news_key가져오기
//	public List<Long> getNewsKeys2(String keyword) {
//		return keywordService.getNewsKeys2(keyword);
//	}
	public void insertKeywordNews(List<Map<String, Object>> keysList) {
		crudService.insertKeywordNews(keysList);
	}

	public List<NewsDTO> getNewsList(int keyword_key) {
		return keywordService.getNewsList(keyword_key);
	}

	public List<NewsDTO> getNewsListLimit(int[] limit) {
		return keywordService.getNewsListLimit(limit);
	}

	public int getTotalNews(int keyword_key) {
		return keywordService.getTotalNews(keyword_key);
	}

//	뉴스 내용들 모두 들고오기
	public List<String> getNewsContent() {
		return searchService.getNewsContent();
	}

}
