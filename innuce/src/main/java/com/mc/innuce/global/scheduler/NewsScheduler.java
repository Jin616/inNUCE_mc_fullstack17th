package com.mc.innuce.global.scheduler;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.innuce.domain.news.selenium.service.CrawlingNewsService;
import com.mc.innuce.domain.news.selenium.webdriver.WebDriverPool;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.global.config.Config;

@Component
public class NewsScheduler {

	@Autowired
	NewsService ns;
	@Autowired
	CrawlingNewsService cns;
	@Autowired
	WebDriverPool pool;
	
	private boolean isCategoryCrawlerRunning = false; // default false !
	private boolean isHeadlineCrawlerRunning = false; // default false !
	
	public static int categoryCrawlerCallCount = 0;
	public static int headlineCrawlerCallCount = 0;
	
	@Scheduled(cron = "0 0/30 * * * *")
	public void schduleUpdateNewsCategory() {
		
		try {
//			if (!isCategoryCrawlerRunning) {
			if (Config.CURRENT_OS.equals("linux") && !isCategoryCrawlerRunning) {
				System.out.println("categoryCrawlerCallCount : " + ++categoryCrawlerCallCount);
				System.out.println("category crawller 실행");
				isCategoryCrawlerRunning = true;
				
				cns.crawllingCategoryNews();
				
				isCategoryCrawlerRunning = false;
				System.out.println("category crawller 종료");
			}
			
		} catch (Exception e) {
			System.out.println("schduleUpdateNewsCategory ERROR!!!");
			pool.showStatusWebDriverPool();
			pool.initWebDriverPool();
			e.printStackTrace();
		}
		
	}

	// 매 시 15, 45분 마다 실행
	@Scheduled(cron = "0 15/30 * * * *")
	public void scheduleUpdateNewsHeadline() {
		
		try {
//			if (!isHeadlineCrawlerRunning) {
			if (Config.CURRENT_OS.equals("linux") && !isHeadlineCrawlerRunning) {
				System.out.println("headlineCrawlerCallCount : " + ++headlineCrawlerCallCount);
				System.out.println("headline crawller 실행");
				String[] categorys = {"정치", "경제", "사회", "생활", "IT", "세계"};
				isCategoryCrawlerRunning = true;
				
				for(int i = 0; i < categorys.length; i++)
					cns.crawlerHeadlineNews(categorys[i], "10" + i);
				isCategoryCrawlerRunning = false;
				System.out.println("headline crawller 종료");
			}
			
		} catch (Exception e) {
			System.out.println("scheduleUpdateNewsHeadline ERROR!!!");
			pool.showStatusWebDriverPool();
			pool.initWebDriverPool();
			e.printStackTrace();
		}
		
	}
	
}