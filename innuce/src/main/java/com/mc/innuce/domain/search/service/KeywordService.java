package com.mc.innuce.domain.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dao.KeywordDAO;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Service
public class KeywordService {
	@Autowired
	KeywordDAO keywordDAO;
	
	public List<NewsDTO> getNewsList(int keyword_key) {
		return keywordDAO.getNewsList(keyword_key);
	}
	public List<NewsDTO> getNewsListLimit(int[] limit) {
		return keywordDAO.getNewsListLimit(limit);
	}
	
	public List<Long> getNewKeys(String keyword) {
		return keywordDAO.getNewKeys(keyword);
	}

	public List<Long> getNewsKeys2(String keyword) {
		return keywordDAO.getNewsKeys2(keyword);
	}

	public int getTotalNews(int keyword_key) {
		return keywordDAO.getTotalNews(keyword_key);
	}





}
