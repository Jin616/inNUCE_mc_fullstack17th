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
	
	public List<NewsDTO> getNewsList(KeywordDTO dto) {
		return keywordDAO.getNewsList(dto);
	}
	
	public long[] getNewsKeys1() {
		return keywordDAO.getNewsKeys1();
	}

	public long[] getNewsKeys2(String keyword) {
		return keywordDAO.getNewsKeys2(keyword);
	}

}
