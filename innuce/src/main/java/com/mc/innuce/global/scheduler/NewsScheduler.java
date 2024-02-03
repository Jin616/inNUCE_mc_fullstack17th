package com.mc.innuce.global.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.innuce.domain.news.selenium.service.CrawlingNewsService;
import com.mc.innuce.domain.news.service.NewsService;

@Component
public class NewsScheduler {

	@Autowired
	NewsService ns;
	@Autowired
	CrawlingNewsService cns;

	private boolean isCategoryCrawllerRunning = true; // default false !
	
	public static List<Long> newsList = new ArrayList<>();

	@Scheduled(cron = "0 0/1 * * * *")
	public void schduleUpdateNewsCategory1() {
		
		System.out.println("category crawller 호출");
		if(!isCategoryCrawllerRunning) {
			System.out.println("category crawller 실행");
			isCategoryCrawllerRunning = true;

			cns.getCategoryNews(new String[]{"100", "101", "102", "103", "104", "105"});
			
			System.out.println("category crawller 종료");
			isCategoryCrawllerRunning = false;
		}
	}

}
