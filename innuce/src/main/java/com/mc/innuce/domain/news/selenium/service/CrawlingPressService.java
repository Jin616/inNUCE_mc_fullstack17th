package com.mc.innuce.domain.news.selenium.service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dto.PressDTO;
import com.mc.innuce.domain.news.selenium.webdriver.WebDriverPool;

/**
 * 네이버 언론사 정보들을 가져오는 크롤러
 * @author JIN 언론사
 */
@Service
public class CrawlingPressService {

	@Autowired
	WebDriverPool pool;
	
	private String rankingURL = "https://news.naver.com/main/ranking/popularDay.naver";

	// rankingURL에서 언론사DTO들을 긁어옴 서버 실행 시 등 주기적으로 한번씩 실행해서 DB 업데이트 해주는 방향으로 설계 예정
	public List<PressDTO> getPressInform() {
		List<PressDTO> pressDTOList = new ArrayList<>();
		WebDriver driver = pool.getWebDriver();
		
		driver.get(rankingURL);
		
		// 요소들을 받아올 리스트 생성
		// By. 메소드를 이용해 원하는 값들을 검색해 가져올 수 있음. _cluster_content는 헤드라인에 있는 뉴스들
		List<WebElement> rankingList = driver.findElements(By.className("rankingnews_box"));
		List<String> pressURLList = new ArrayList<>();

		for (WebElement e : rankingList)
			pressURLList.add(e.findElement(By.className("rankingnews_box_head")).getAttribute("href"));

		for (String url : pressURLList) {
			PressDTO dto = new PressDTO();
			dto.setPress_navermain(url.substring(0, url.indexOf("press") + 9));

			String pressKey = url.substring(url.indexOf("press") + 6);
			dto.setPress_key(Integer.parseInt(pressKey.substring(0, 3)));

			driver.get(url);
			dto.setPress_name(driver.findElement(By.className("press_hd_name_link")).getText());
			dto.setPress_logo(driver.findElement(By.className("press_hd_ci_image")).findElement(By.tagName("img")).getAttribute("src"));
			dto.setPress_mainpage(driver.findElement(By.className("press_hd_name_link")).getAttribute("href"));
			
			pressDTOList.add(dto);
		}

		pool.releaseWebDriver(driver);
		return pressDTOList;
	}

}
