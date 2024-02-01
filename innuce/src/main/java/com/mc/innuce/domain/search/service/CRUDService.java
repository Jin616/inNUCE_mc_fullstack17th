package com.mc.innuce.domain.search.service;


import java.util.List;
import java.util.Map;

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
	public int updateKeyword(String keyword) {
		return dao.updateKeyword(keyword);
	}
	public int insertKeyword(String keyword) {
		return dao.insertKeyword(keyword);
	}
	public void insertKeywordNews(List<Map<String, Object>> keysList) {
		dao.insertKeywordNews(keysList);
	}
}
