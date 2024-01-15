package com.mc.innuce.domain.news.selenium.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.naverapi.SentimentService;
import com.mc.innuce.domain.news.naverapi.SummaryService;
import com.mc.innuce.domain.news.selenium.webdriver.WebDriverWithOption;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.global.util.hreftonewsdto.WebConverter;

/**
 * 
 * @author JIN
 *
 */
@Service
public class CrawlingNewsService {
	
	@Autowired
	NewsService newsService;
	@Autowired
	SummaryService summaryService;
	@Autowired
	SentimentService sentimentService;
	
	WebConverter conv = new WebConverter();
	
	private WebDriver driver;
	String url = "https://news.naver.com/main/main.naver?mode=LSD&sid1=";
	String[] category = {"100"};//, "101", "102", "103", "104", "105"};
	
	public List<NewsDTO> getCategoryNews() {
		driver = new WebDriverWithOption().getDriver();
		
		HashSet<String> dbHash = new HashSet<>();
		List<NewsDTO> resultList = new ArrayList<>();
		for(long i : newsService.getAllNewsListOnlyKey())
			dbHash.add(String.format("%13d", i));
		
		WebDriver newsdriver = new WebDriverWithOption().getDriver();
		for(String s : category) {
			driver.get(url + s);
			List<WebElement> dt = driver.findElements(By.className("photo"));
			
			System.out.println("가져온 dt 개수는 " + dt.size());
			for(WebElement e : dt) {
				String href = e.findElement(By.tagName("a")).getAttribute("href");
				// https://n.news.naver.com/mnews/article/437/0000375044?sid=104
				int index = href.indexOf("?");
				String newsKey = href.substring(index - 10, index);
				String pressKey = href.substring(index - 14, index - 10);
				if(dbHash.add(pressKey + newsKey)) {
					
					NewsDTO dto = parseNewsToNewsDTO(newsdriver, href);
					dto.setNews_thumbnailuri(e.findElement(By.tagName("img")).getAttribute("src"));
					resultList.add(dto);

				}
			}
		}
		
		newsdriver.quit();
		driver.quit();
		return resultList;
	}

	private NewsDTO parseNewsToNewsDTO(WebDriver newsdriver, String href) {
		NewsDTO news = new NewsDTO();
		newsdriver.get(href);
		
		news.setNews_key(conv.getLongNewsKey(href));
		news.setPress_key(conv.getIntPressKey(href));
		news.setNews_title(newsdriver.findElement(By.id("title_area")).getText());
		
		String content = newsdriver.findElement(By.id("dic_area")).getText();
		
		List<WebElement> tem = newsdriver.findElements(By.tagName("strong"));
		for(WebElement replaceText : tem)
			content = content.replace(replaceText.getText(), "");
		
		tem = newsdriver.findElements(By.className("img_desc"));
		for(WebElement replaceText : tem)
			content = content.replace(replaceText.getText(), "");
		
		news.setNews_content(content);
		news.setNews_writendate(conv.getStringToTimestamp(newsdriver.findElement(By.className("_ARTICLE_DATE_TIME")).getAttribute("data-date-time")));
		List<WebElement> updatediv = newsdriver.findElements(By.className("_ARTICLE_MODIFY_DATE_TIME"));
		
		if(updatediv.size() == 0)
			news.setNews_updatedate(null);
		else
			news.setNews_updatedate(conv.getStringToTimestamp(updatediv.get(0).getAttribute("data-modify-date-time")));
		news.setNews_pulldate(new Timestamp(System.currentTimeMillis()));
		
		List<WebElement> writer = newsdriver.findElements(By.className("byline_s"));
		if(writer.size() == 0)
			news.setNews_writer("원문 참조");
		else
			news.setNews_writer(writer.get(0).getText());
		
		news.setNews_uri(href);
		news.setNews_originuri(newsdriver.findElement(By.className("media_end_head_origin_link")).getAttribute("href"));
		news.setNews_deleted(false);
		news.setNews_deleteddate(null);
		news.setNews_deletedcode(0);
		news.setNews_category(newsdriver.findElement(By.className("is_active")).findElement(By.tagName("span")).getText());
		//news.setNews_thumbnailuri();
		List<WebElement> imgdiv = newsdriver.findElements(By.id("img1"));
		if(imgdiv.size() == 0)
			news.setNews_thumbnailuri2(null);
		else
			news.setNews_thumbnailuri2(newsdriver.findElement(By.id("img1")).getAttribute("src"));
		
		news.setSumm_content(summaryService.summary(news.getNews_title(), news.getNews_content()));
		
		HashMap<String, String> sentimentMap = sentimentService.sentiment(news.getSumm_content());
		news.setSumm_sentiment(sentimentMap.get("sentiment"));
		news.setSumm_sentimentpercent(sentimentMap.get("percent"));
		System.out.println("done");
		return news;
	}
	
}
