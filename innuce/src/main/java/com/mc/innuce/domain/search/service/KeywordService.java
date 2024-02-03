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

	
//	seo
	public int updateKeyword(KeywordDTO dto) {
		return keywordDAO.updateKeyword(dto);
	}

	public int insertKeyword(String keyword, LocalDate dateStart) {
		KeywordDTO dto = new KeywordDTO();

		dto.setKeyword_content(keyword);
		dto.setKeyword_recent_time(Timestamp.valueOf(dateStart.atStartOfDay()));

		return insertKeyword(dto);
	}
	public int insertKeyword(String keyword) {
		KeywordDTO dto = new KeywordDTO();

		dto.setKeyword_content(keyword);
		dto.setKeyword_recent_time(Timestamp.valueOf(LocalDateTime.now()));

		return insertKeyword(dto);
	}
	public int insertKeyword(KeywordDTO dto) {
		return keywordDAO.insertKeyword(dto);
	}

	public void insertKeywordNews(KeysDTO keysDTO) {
		keywordDAO.insertKeywordNews(keysDTO);
	}

}
