package com.mc.innuce.domain.user.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.user.dao.ScrapDAO;
import com.mc.innuce.domain.user.dao.UserDAO;

@Service
public class ScrapService {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	ScrapDAO scrapDao;
	
	// 스크랩 기능
	public void scrapNews(int user_key, long news_key) {
		System.out.println("서비스 도착");
		HashMap map = new HashMap();
		BigInteger BI_news_key = BigInteger.valueOf(news_key);
		map.put("user_key", user_key);
		map.put("news_key", BI_news_key);
		
		int result = scrapDao.scrapNews(map);
		System.out.println("추가된 행"+result);
		System.out.println("서비스통과");
	}
	
	// user_key 가지고 내가 한 스크랩 기사 수 가져오기
	public int countMyTotalScrap(int user_key) {
		
		return scrapDao.countMyTotalScrap(user_key);
	}

	// user_key 가지고 내가 스크랩한 NewsDTO 들 가져오기
	public List<NewsDTO> getMyAllScrap(int user_key) {
		
		List<NewsDTO> scrap_list = scrapDao.getMyAllScrap(user_key);
		System.out.println("service단"+scrap_list.size());
		return scrap_list;
	}
	
	//user_key를 가지고 입력한 news_title이 포함된 내가 스크랩한 기사 수 찾기
	public int countMyTotalScrapByTitle(int user_key, String news_title) {
		HashMap map = new HashMap();
		map.put("user_key", user_key);
		map.put("news_title", news_title);
		return scrapDao.countMyTotalScrapByTitle(map);
	}
	
	//user_key를 가지고 입력한 news_content가 포함된 내가 스크랩한 기사 수 찾기
	public int countMyTotalScrapByContent(int user_key, String news_content) {
		HashMap map = new HashMap();
		map.put("user_key", user_key);
		map.put("news_content", news_content);
		return scrapDao.countMyTotalScrapByContent(map);
	}
	
	//user_key를 가지고 입력한 news_title이 포함된 내가 스크랩한 NewsDTO들 찾기
	public List<NewsDTO> getMyAllScrapByTitle(int user_key, String news_title) {
		HashMap map = new HashMap();
		map.put("user_key", user_key);
		map.put("news_title", news_title);
		return scrapDao.getMyAllScrapByTitle(map);
	}
	
	//user_key를 가지고 입력한 news_content가 포함된 내가 스크랩한 NewsDTO들 찾기
	public List<NewsDTO> getMyAllScrapByContent(int user_key, String news_content) {
		HashMap map = new HashMap();
		map.put("user_key", user_key);
		map.put("news_content", news_content);
		return scrapDao.getMyAllScrapByContent(map);
	}
	
	// 스크랩 취소기능
	public void cancelScrapNews(int user_key, long news_key) {
		System.out.println("서비스 도착");
		HashMap map = new HashMap();
		BigInteger BI_news_key = BigInteger.valueOf(news_key);
		map.put("user_key", user_key);
		map.put("news_key", BI_news_key);
		
		int result = scrapDao.cancelScrapNews(map);
		System.out.println("삭제된 행"+result);
		System.out.println("서비스통과");
		
	}
	
	
}
