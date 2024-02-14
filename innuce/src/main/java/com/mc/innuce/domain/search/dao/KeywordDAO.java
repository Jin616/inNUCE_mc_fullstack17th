package com.mc.innuce.domain.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.dto.KeysDTO;

@Repository
@Mapper
public interface KeywordDAO {
	
	public List<NewsDTO> getNewsListLimit(Map<String,Object> map);
	public int getTotalNews(int keyword_key);
	
	public List<Long> getNewsKeys(String keyword);
	
	public List<Long> getNewsKeys2(String place);
	public List<String> getCategoryContent(String category);
	
	KeywordDTO oneKeyword(String keyword);
	int updateKeyword(String keyword);
	int insertKeyword(String keyword);
	int insertKeywordNews(KeysDTO keysDTO);
	public int deleteKeyword(String token);
	
	// seo start
	int optionPeriodSearch(Map<String, Object> map);

	int optionPressSearch(Map<String, Object> map);

	int optionPeriodPressSearch(Map<String, Object> map);

	public List<NewsDTO> getNewsListLimitOptionPeriod(Map<String, Object> map);
	
	public List<NewsDTO> getNewsListLimitOptionPress(Map<String, Object> map);
	
	public List<NewsDTO> getNewsListLimitOptionPressPeriod(Map<String, Object> map);
	// seo end


}