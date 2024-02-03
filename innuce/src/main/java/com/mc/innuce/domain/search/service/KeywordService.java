package com.mc.innuce.domain.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dao.KeywordDAO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.dto.KeysDTO;

@Service
public class KeywordService {
	@Autowired
	KeywordDAO keywordDAO;

	public List<NewsDTO> getNewsList(Map<String, Integer> map) {
		return keywordDAO.getNewsList(map);
	}

	public List<NewsDTO> getNewsListLimit(Map<String, Object> map) {
		return keywordDAO.getNewsListLimit(map);
	}

//	키워드기반 검색
	public List<Long> getNewKeys(String keyword) {
		return keywordDAO.getNewKeys(keyword);
	}

//	위치기반 검색
	public List<Long> getNewKeys2(String place) {
		return keywordDAO.getNewKeys2(place);
	}

//	public List<Long> getNewsKeys2(String keyword) {
//		return keywordDAO.getNewsKeys2(keyword);
//	}

	public int getTotalNews(int keyword_key) {
		return keywordDAO.getTotalNews(keyword_key);
	}

	public KeywordDTO oneKeyword(String keyword) {
		return keywordDAO.oneKeyword(keyword);
	}

	public int updateKeyword(String keyword) {
		return keywordDAO.updateKeyword(keyword);
	}

	public int insertKeyword(String keyword) {
		return keywordDAO.insertKeyword(keyword);
	}

	public void insertKeywordNews(KeysDTO keysDTO) {
		keywordDAO.insertKeywordNews(keysDTO);
	}

}
