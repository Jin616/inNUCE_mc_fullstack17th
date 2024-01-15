package com.mc.innuce.domain.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Service
public class ComponentService {

	@Autowired
	KeywordService keywordService;
	@Autowired
	SearchService searchService;
	@Autowired
	CRUDService crudService;
	
	public KeywordDTO oneKeyword(String keyword) {
		return crudService.oneKeyword(keyword);
	}
	public int updateKeyword(KeywordDTO dto) {
		return crudService.updateKeyword(dto);
	}
	
	public int insertKeyword(KeywordDTO dto) {
		return crudService.insertKeyword(dto);
	}
//  키워드가 없을때 news_key가져오기
	public long[] getNewsKeys1() {
		return keywordService.getNewsKeys1();
	}
//	키워드가 있을때 news_key가져오기
	public long[] getNewsKeys2(String keyword) {
		return keywordService.getNewsKeys2(keyword);
	}
	public void insertToKeywordNews(int keyword_key, long[] newsKeyArray) {
		crudService.insertToKeywordNews(keyword_key,newsKeyArray);
	}
	public List<NewsDTO> getNewsList(KeywordDTO dto) {
		return keywordService.getNewsList(dto);
	}

	
}
