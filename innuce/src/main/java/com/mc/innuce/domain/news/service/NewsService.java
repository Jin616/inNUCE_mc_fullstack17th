package com.mc.innuce.domain.news.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dao.NewsDAO;
import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.global.util.jsonparsefromdto.JSONParser;

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


	// 카테고리 별 뉴스를 넘겨주는 
	public JSONObject getHeadlineNews() {
		JSONObject result = new JSONObject();
		JSONParser parser = new JSONParser();

		String[] categorys = {"정치", "경제", "사회", "생활/문화", "세계", "IT/과학"};
		
		for(int i = 0; i < categorys.length; i++)
			result.put(""+i, parser.getJsonArrayNews(newsdao.selectHeadLineNews(categorys[i])));
		
		return result;
	}

	// 카테고리별 헤드라인 뉴스를 insert 하는
	public void insertNewsHeadline(List<NewsDTO> list) {
		newsdao.insertNewsHeadline(list);
	}
	

	
	// 스크랩에서 쓸 news_key 를 가지고 한개의 newsDTO 가져오기 (김)
	public NewsDTO selectOne(long news_key) {
		
		return newsdao.selectSingleNews(news_key);
	}

	public Object getKeywordNews() {
		JSONObject result = new JSONObject();
		JSONParser parser = new JSONParser();

		List<Integer> keywordKey = newsdao.selectTop3KeywordKey(); 
		
		for (int i = 0; i < keywordKey.size(); i++) {
			result.put(""+i, parser.getJsonArrayNews(newsdao.selectKeywordNews(keywordKey.get(i))));
		}
		
		return result;
	}

	public List<String> getKeywordNews2() {
		List<Integer> keywordKey = newsdao.selectTop3KeywordKey();
		for (Integer integer : keywordKey) {
			System.out.println(integer);
		}
		return newsdao.getKeyword(keywordKey);
	}

	public List<Integer> selectTop4KeywordKey() {
		// TODO Auto-generated method stub
		return newsdao.selectTop4KeywordKey();
	}

}
