package com.mc.innuce.domain.search.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dao.KeywordDAO;
import com.mc.innuce.domain.search.dto.KeysDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Service
public class KeywordService {
	@Autowired
	KeywordDAO keywordDAO;


	public List<NewsDTO> getNewsListLimit(Map<String, Object> map) {
		return keywordDAO.getNewsListLimit(map);
	}

//	키워드기반 검색
	public List<Long> getNewsKeys(String keyword) {
		return keywordDAO.getNewsKeys(keyword);
	}

//	위치기반 검색
	public List<Long> getNewsKeys2(String place) {
		return keywordDAO.getNewsKeys2(place);
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
	

	public int insertKeywordNews(KeysDTO keysDTO) {
		return keywordDAO.insertKeywordNews(keysDTO);
	}

	public int deleteKeyword(String token) {
		return keywordDAO.deleteKeyword(token);
	}


}