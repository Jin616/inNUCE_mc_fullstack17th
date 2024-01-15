package com.mc.innuce.domain.news.selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * @author JIN
 * 
 * 
 */
public class SeleniumTest {
	
	private void crawling(String url) {
		System.out.println("드라이버를 연결 중...");
		WebDriver driver = getDriver(url);
		System.out.println("드라이버 연결 성공!");
		
		// 요소들을 받아올 리스트 생성
		// By. 메소드를 이용해 원하는 값들을 검색해 가져올 수 있음. _cluster_content는 헤드라인에 있는 뉴스들
		WebElement sectionBody = driver.findElement(By.className("section_body"));
		
		List<WebElement> newsList = sectionBody.findElements(By.tagName("dt"));
		List<WebElement> thumbUri = new ArrayList<>(); // use
		List<WebElement> naverUri = new ArrayList<>();
		for(int i = 0; i < newsList.size(); i++) {
			if(i % 2 == 0) {
				naverUri.add(newsList.get(i).findElement(By.tagName("a")));
				thumbUri.add(newsList.get(i).findElement(By.tagName("img")));
			}
		}
		
		for(WebElement news : naverUri) {
			driver.get(news.getAttribute("href"));
		}
		
		System.out.println(naverUri.get(0).getAttribute("href"));
		System.out.println(thumbUri.get(0).getAttribute("src"));
		
		
		System.out.println("드라이버 종료 중...");
		driver.quit();
	}

	private static WebDriver getDriver(String url) {
		ChromeOptions chromeOptions = new ChromeOptions();
		
		//chromeOptions.addArguments("--lang=ko");
		//chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--incognito"); // 시크릿모드로 열기
		chromeOptions.addArguments("--disable-extensions"); // 확장기능 비활성화
		chromeOptions.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용 비활성화
		chromeOptions.addArguments("--disable-gpu"); // gpu 사용 x
		//chromeOptions.addArguments("--start-maximized"); // 시작할때 최대사이즈로
		chromeOptions.addArguments("--disable-popup-blocking"); // 팝업 무시
		chromeOptions.addArguments("headless"); // 브라우저 화면 실행 x
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get(url);
		// 완전히 페이지가 로딩될때까지 대기
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		return driver;
	}
}
