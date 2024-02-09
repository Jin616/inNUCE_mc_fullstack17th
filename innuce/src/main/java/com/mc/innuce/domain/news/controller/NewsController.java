package com.mc.innuce.domain.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
}
