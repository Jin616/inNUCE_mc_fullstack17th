package com.mc.innuce;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.search.dto.KeyOfKeywordAndNewsDTO;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.service.ComponentService;

@Controller
public class KeywordController {
	
	@Autowired
	ComponentService compoService;
	
	@GetMapping("/mainSearch")
	public ModelAndView mainSearch(String keyword) {
		
		ModelAndView mv = new ModelAndView();
		List<NewsDTO> newsList=new ArrayList<>();
		List<Long> newsKeyList=null; //News
		KeywordDTO dto = compoService.oneKeyword(keyword);
		
//		코모란
//		dd
		
		if(dto != null) {
			// keyword_content가 있음
			compoService.updateKeyword(dto);
			newsKeyList=compoService.getNewsKeys2(keyword);
		} else {
		 // keyword_content가 없음
			compoService.insertKeyword(dto);
			
			//db거치지않고 news_key 들고오기
			
			
			//db거치고 news_key 들고오기
			newsKeyList=compoService.getNewsKeys1();
		}
		
		
//	news table 에서 검색어에 해당하는 news_key들을 insert
//	keyword_news table에 해당검색어와 여러 개의 news_key insert
//	insert into keyword_news(news_key,keyword_key) values( , );
		KeyOfKeywordAndNewsDTO kkndto = new KeyOfKeywordAndNewsDTO(dto.getKeyword_key(),newsKeyList);
		compoService.insertToKeywordNews(kkndto);
		
		
		newsList=compoService.getNewsList(dto);
		// news_key -> news table news_key
		mv.addObject("newsList", newsList);
		
		
		mv.setViewName("search/searchPage");

		return mv;
	}
	
	
//검색 -> 네이버 뉴스 -> news 테이블에 해당하는 news_key가 여러 개 
//-> keyword_news에  news_key 등록
}
