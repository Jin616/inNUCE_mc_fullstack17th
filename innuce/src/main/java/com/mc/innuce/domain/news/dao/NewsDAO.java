package com.mc.innuce.domain.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.news.dto.NewsAndPressKeyDTO;
import com.mc.innuce.domain.news.dto.NewsDTO;

/**
 * 
 * @author JIN
 *
 */
@Repository
@Mapper
public interface NewsDAO {

	public int countAllNews();

	public List<Long> getAllNewsListOnlyKey();
	
	public int insertListNews(List<NewsDTO> newsList);
	
}