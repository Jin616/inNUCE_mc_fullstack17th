package com.mc.innuce.domain.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dao.NewsDAO;
import com.mc.innuce.domain.news.dto.NewsAndPressKeyDTO;
import com.mc.innuce.domain.news.dto.NewsDTO;

/**
 * @author JIN
 */
@Service
public class NewsService {

	@Autowired
	NewsDAO newsdao;
	
	public int countAllNews() {
		return newsdao.countAllNews();
	}
	
	public List<Long> getAllNewsListOnlyKey() {
		return newsdao.getAllNewsListOnlyKey();
	}
	
	public int insertNewsList(List<NewsDTO> newsList) {
		return newsdao.insertListNews(newsList);
	}
	
}
