package com.mc.innuce.domain.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;

@Repository
@Mapper
public interface KeywordDAO {
	
	public List<NewsDTO> getNewsList(KeywordDTO dto);
	public long[] getNewsKeys1();
	public long[] getNewsKeys2(String keyword);
}
