package com.mc.innuce.global.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mc.innuce.domain.news.selenium.service.CrawlingNewsService;
import com.mc.innuce.domain.news.service.NewsService;

@Component
public class NewsScheduler {

	@Autowired
	NewsService ns;
	@Autowired
	CrawlingNewsService cns;
	
	public static List<Long> newsList = new ArrayList<>();
	
//	@Scheduled(cron = "0 0/30 * * * *")
//	public void schduleUpdateNewsCategory1() {
//		
//		ns.insertNewsList(cns.getCategoryNews("100"));
//	}
	
//	@Scheduled(cron = "0 2/10 * * * *")
//	public void schduleUpdateNewsCategory2() {
//		ns.insertNewsList(cns.getCategoryNews("101"));
//	}
//	
//	@Scheduled(cron = "0 4/10 * * * *")
//	public void schduleUpdateNewsCategory3() {
//		ns.insertNewsList(cns.getCategoryNews("102"));
//	}
//	
//	@Scheduled(cron = "0 7/20 * * * *")
//	public void schduleUpdateNewsCategory4() {
//		ns.insertNewsList(cns.getCategoryNews("103"));
//	}
//	
//	@Scheduled(cron = "0 8/20 * * * *")
//	public void schduleUpdateNewsCategory5() {
//		ns.insertNewsList(cns.getCategoryNews("104"));
//	}
//	
//	@Scheduled(cron = "0 9/20 * * * *")
//	public void schduleUpdateNewsCategory6() {
//		ns.insertNewsList(cns.getCategoryNews("105"));
//	}
	
}
