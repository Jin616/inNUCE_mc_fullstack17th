package com.mc.innuce.domain.search.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.search.dto.KeyOfKeywordAndNewsDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Repository
@Mapper
public interface CrudDAO {
	KeywordDTO oneKeyword(String keyword);
	int updateKeyword(KeywordDTO dto);
	int insertKeyword(KeywordDTO dto);
	void insertToKeywordNews(KeyOfKeywordAndNewsDTO dto);
	
}
