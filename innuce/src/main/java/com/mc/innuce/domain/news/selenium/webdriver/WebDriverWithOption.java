package com.mc.innuce.domain.news.selenium.webdriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * @author JIN
 *
 */
public class WebDriverWithOption {

	/**
	 * option 설정한 driver를 생성 후 반환
	 */
	public WebDriver getDriver(String url) {
		System.out.println("Driver Connecting...");
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
		
		System.out.println("Connection Success!!");
		
		return driver;
	}
	
	public WebDriver getDriver() {
		return getDriver("https://news.naver.com/");
	}
	
}
