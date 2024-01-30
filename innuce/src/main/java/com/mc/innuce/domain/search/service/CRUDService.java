package com.mc.innuce.domain.search.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.search.dao.CrudDAO;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Service
public class CRUDService {
	@Autowired
	CrudDAO dao;

	public KeywordDTO oneKeyword(String keyword) {
		return dao.oneKeyword(keyword);
	}
	public int updateKeyword(KeywordDTO dto) {
		return dao.updateKeyword(dto);
	}
	
	public int insertKeyword(KeywordDTO dto) {
		return dao.insertKeyword(dto);
	}
	
	// seo 01-19 키워드와 검색 시작 날짜를 받아서 insert 하는 메서드
	public int insertKeyword(String keyword, LocalDate dateStart) {
		KeywordDTO dto = new KeywordDTO();
		
		dto.setKeyword_content(keyword);
		dto.setKeyword_recent_time(Timestamp.valueOf(dateStart.atStartOfDay()));
		
		return insertKeyword(dto);
	}
	
	// seo 01-19 키워드를 받아서 오늘 날짜로 insert 하는 메서드
	public int insertKeyword(String keyword) {
		KeywordDTO dto = new KeywordDTO();
		
		dto.setKeyword_content(keyword);
		dto.setKeyword_recent_time(Timestamp.valueOf(LocalDateTime.now()));
		
		return insertKeyword(dto);
	}
	
	public void insertToKeywordNews(int keyword_key, long[] newsKeyArray) {
		dao.insertToKeywordNews(keyword_key,newsKeyArray);
	}
}
