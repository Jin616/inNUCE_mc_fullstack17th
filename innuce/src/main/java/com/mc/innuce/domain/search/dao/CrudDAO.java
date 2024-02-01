package com.mc.innuce.domain.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.search.dto.KeywordDTO;

@Repository
@Mapper
public interface CrudDAO {
	KeywordDTO oneKeyword(String keyword);
	int updateKeyword(String keyword);
	int insertKeyword(String keyword);
	void insertKeywordNews(List<Map<String, Object>> keysList);
	
}
