package com.mc.innuce.domain.search.service;

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
	public void insertToKeywordNews(int keyword_key, long[] newsKeyArray) {
		dao.insertToKeywordNews(keyword_key,newsKeyArray);
	}
}
