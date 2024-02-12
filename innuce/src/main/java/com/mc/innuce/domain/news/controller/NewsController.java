package com.mc.innuce.domain.news.controller;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.selenium.webdriver.WebDriverPool;
import com.mc.innuce.domain.news.service.NewsService;

/**
 * @author JIN
 */
@RestController
public class NewsController {

	@Autowired
	NewsService ns;
	@Autowired
	WebDriverPool pool;
	
	@GetMapping("headline")
	@ResponseBody
	public String headline() {
		return ns.getHeadlineNews().toString(); 
	}
	
	@GetMapping("/news/{newsKey}")
	public ModelAndView showNews(@PathVariable String newsKey) {
		ModelAndView mv = new ModelAndView();

		NewsDTO dto = ns.selectOne(newsKey);

		mv.addObject("News", dto);
		System.out.println(dto);
		mv.setViewName("search/news");

		return mv;
	}
	
	//////////////////////////////////////////////////////
	// testlink
	@GetMapping("/testleek/{n}")
	public void testLeek(@PathVariable int n) {
		ChromeOptions chromeOptions = new ChromeOptions();
		
		chromeOptions.addArguments("--lang=ko_KR.utf-8");
		chromeOptions.addArguments("--incognito"); // 시크릿모드로 열기
		chromeOptions.addArguments("--disable-extensions"); // 확장기능 비활성화
		chromeOptions.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용 비활성화
		chromeOptions.addArguments("--disable-gpu"); // gpu 사용 x
		chromeOptions.addArguments("--disable-popup-blocking"); // 팝업 무시
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-application-cache");
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		for(int i = 0; i < n; i++) {
			WebDriver driver = new ChromeDriver(chromeOptions);
			driver.quit();
		}
	}
	
	@GetMapping("/testshowdriver")
	public void showPool() {
		pool.showStatusWebDriverPool();
	}
	
	@GetMapping("/testinitpool")
	public void initPool() {
		pool.initWebDriverPool();
	}
	
}
