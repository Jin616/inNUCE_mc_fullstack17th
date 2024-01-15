package com.mc.innuce.domain.news.selenium.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.dto.PressDTO;
import com.mc.innuce.domain.news.selenium.webdriver.WebDriverWithOption;

/**
 * 
 * @author JIN
 * 언론사 
 * 
 */
@Service
public class CrawlingPressService {
	
	private String rankingURL = "https://news.naver.com/main/ranking/popularDay.naver";
    private String[] sid = {"100, 101, 102, 103, 104, 105"}; // 카테고리 아이디
	private String categoryURL = "https://news.naver.com/main/main.naver?sid1=100";
	private WebDriver driver;
	
	/**
	 * rankingURL에서 언론사DTO들을 긁어옴 서버 실행 시 한번씩 실행해서 DB 업데이트 해주는 방향으로 설계 예정 
	 */
	public List<PressDTO> getPressInform() {
		List<PressDTO> pressDTOList = new ArrayList<>();
		driver = new WebDriverWithOption().getDriver(rankingURL);
		
		// 요소들을 받아올 리스트 생성
		// By. 메소드를 이용해 원하는 값들을 검색해 가져올 수 있음. _cluster_content는 헤드라인에 있는 뉴스들
		List<WebElement> rankingList = driver.findElements(By.className("rankingnews_box"));
		List<String> pressURLList = new ArrayList<>();
		
		for(WebElement e : rankingList) {
			// if(pressURLList.size() > 10) break; // temp for testing need delete
			pressURLList.add(e.findElement(By.className("rankingnews_box_head")).getAttribute("href"));
		}
		
		for(String url : pressURLList) {
			PressDTO dto = new PressDTO();

			dto.setPress_navermain(url.substring(0, url.indexOf("press")+9));
			String pressKey = url.substring(url.indexOf("press")+6);
			dto.setPress_key(Integer.parseInt(pressKey.substring(0, 3)));
			
			driver.get(url);
			dto.setPress_name(driver.findElement(By.className("press_hd_name_link")).getText());
			dto.setPress_logo(driver.findElement(By.className("press_hd_ci_image")).findElement(By.tagName("img")).getAttribute("src"));

			dto.setPress_mainpage(driver.findElement(By.className("press_hd_name_link")).getAttribute("href"));
			System.out.println(dto.toString());
			pressDTOList.add(dto);
		}
		
		System.out.println("드라이버 종료 중...");
		driver.quit();
		return pressDTOList;
	}

}
