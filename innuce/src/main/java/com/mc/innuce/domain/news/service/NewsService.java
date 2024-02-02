package com.mc.innuce.domain.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dao.NewsDAO;
import com.mc.innuce.domain.news.dto.NewsDTO;

/**
 * @author JIN
 */
@Service
public class NewsService {

	@Autowired
	NewsDAO newsdao;

	// DB에 저장되어 있는 모든 뉴스의 개수를 리턴
	public int countAllNews() {
		return newsdao.countAllNews();
	}

	// DB에 저장되어 있는 모든 뉴스의 키 리스트를 반환
	public List<Long> getAllNewsListOnlyKey() {
		return newsdao.getAllNewsListOnlyKey();
	}

	// DB에 dto들을 list로 추가하고(중복 무시) 추가된 값을 리턴
	public int insertNewsList(List<NewsDTO> newsList) {
		if (newsList.size() == 0)
			return 0;
		return newsdao.insertListNews(newsList);
	}

	// DB에 news_key들을 넘겨주고 그에 해당하는 List<NewsDTO>를 받아옴
	public List<NewsDTO> getNewsListWithKey(List<Long> keyList) {
		return newsdao.selectNewsListWithKey(keyList);
	}

	// DB에 keyword로 타이틀과 content에서 검색 수행 후 조건에 맞는 List<NewsDTO>를 받아옴
	public List<NewsDTO> selectNewsListSearchWithKeyword(String keyword) {
		return newsdao.selectNewsListSearchWithKeyword(keyword);
	}

	public NewsDTO selectOne(String newsKey) {
		return newsdao.selectOneNews(newsKey);
	}
}
