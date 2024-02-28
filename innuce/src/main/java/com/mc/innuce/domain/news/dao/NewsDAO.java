package com.mc.innuce.domain.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

	public List<NewsDTO> selectNewsListWithKey(List<Long> keyList);

	public List<NewsDTO> selectNewsListSearchWithKeyword(String keyword);

	public NewsDTO selectOneNews(String newsKey);
	
	// news key로 NewsDTO 가져오기 (김)
	public NewsDTO selectSingleNews(long news_key);

	public List<NewsDTO> selectHeadLineNews(String category);

	public void insertNewsHeadline(List<NewsDTO> list);

	public List<Integer> selectTop3KeywordKey();

	public List<NewsDTO> selectKeywordNews(int keyword_key);

	public List<String> getKeyword(List<Integer> keywordKey);

	public List<Integer> selectTop4KeywordKey();

}