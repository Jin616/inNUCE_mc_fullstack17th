package com.mc.innuce.domain.news.selenium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.selenium.service.CrawlingNewsService;
import com.mc.innuce.domain.news.selenium.service.CrawlingPressService;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.news.service.PressService;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	CrawlingNewsService cns;
	@Autowired
	CrawlingPressService cps;
	@Autowired
	NewsService ns;
	@Autowired
	PressService ps;
	
	@GetMapping("search1") 
	public void searchDefault() {
		System.out.println("search default");
		
		List<NewsDTO> list = cns.getSearchNewsDefault("이재명");
		System.out.println("size : " + list.size());
		
		for(NewsDTO dto : list)
			System.out.println(dto);
	}
	
	@GetMapping("press") 
	public void updatePress() {
		System.out.println("update press table");
		ps.updatePressTable(cps.getPressInform());
	}
	
	@GetMapping("count")
	public void count() {
		System.out.println(ns.countAllNews());
	}
	
	@GetMapping("search")
	public void search() {
		cns.getSearchNews("윤석열");
	}
	
	@GetMapping("test1")
	public void test() {
		System.out.println("test1 mapping");
		cns.getCategoryNews(new String[]{"102", "104"});
	}
	
	@GetMapping("test2")
	public void test1() {
		System.out.println("test2 mapping");
		cns.getCategoryNews(new String[]{"100", "101", "103", "105"});
	}
	
}
