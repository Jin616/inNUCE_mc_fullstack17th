package com.mc.innuce.domain.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.news.dto.NewsDTO;

@Repository
@Mapper
public interface ScrapDAO {
	
	// 스크랩 기능
	int scrapNews(HashMap map);
	
	// user_key 가지고 내가 스크랩한 기사 수 가져오기 
	int countMyTotalScrap(int user_key);
	
	// user_key 가지고 내가 스크랩한 newsDTO 가져오기
	List<NewsDTO> getMyAllScrap(int user_key);
	
	//user_key를 가지고 입력한 news_title이 포함된 내가 스크랩한 기사 수 찾기
	int countMyTotalScrapByTitle(HashMap map);
	
	//user_key를 가지고 입력한 news_content가 포함된 내가 스크랩한 기사 수 찾기
	int countMyTotalScrapByContent(HashMap map);
	
	//user_key를 가지고 입력한 news_title이 포함된 내가 스크랩한 newsdto 찾기
	List<NewsDTO> getMyAllScrapByTitle(HashMap map);
	
	//user_key를 가지고 입력한 news_content가 포함된 내가 스크랩한 newsdto 찾기
	List<NewsDTO> getMyAllScrapByContent(HashMap map);

	// 스크랩 취소기능
	int cancelScrapNews(HashMap map);

}
