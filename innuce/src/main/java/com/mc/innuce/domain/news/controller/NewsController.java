package com.mc.innuce.domain.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.service.NewsService;

@RestController
public class NewsController {

	@Autowired
	NewsService ns;
	
	@GetMapping("headline")
	@ResponseBody
	public String headline() {
		return ns.getHeadlineNews().toString(); 
	}
	
	// 0208 seo
	@RequestMapping("/news/{newsKey}")
	public ModelAndView showNews(@PathVariable String newsKey) {
		ModelAndView mv = new ModelAndView();

		NewsDTO dto = ns.selectOne(newsKey);

		mv.addObject("News", dto);
		System.out.println(dto);
		mv.setViewName("search/news");

		return mv;

	}
	
}
