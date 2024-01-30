package com.mc.innuce.domain.news.selenium.webdriver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

/**
 * Chrome 브라우저로 Selenium WebDriver 인스턴스를 관리하기 위한 Pool 
 * @author JIN
 */
@Component
public class WebDriverPool {
	
	private static final int MAX_POOL_SIZE = 4;
	private static final BlockingQueue<WebDriver> webDriverPool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);
	
	static {
		for(int i = 0; i < MAX_POOL_SIZE; i++) {
			webDriverPool.offer(createNewWebDriver());
		}
		System.out.println("driver pool 생성 완료");
	}

	public static WebDriver createNewWebDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		
		chromeOptions.addArguments("--incognito"); // 시크릿모드로 열기
		chromeOptions.addArguments("--disable-extensions"); // 확장기능 비활성화
		chromeOptions.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용 비활성화
		chromeOptions.addArguments("--disable-gpu"); // gpu 사용 x
		chromeOptions.addArguments("--disable-popup-blocking"); // 팝업 무시
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-application-cache");
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		WebDriver driver = new ChromeDriver(chromeOptions);
		
		System.out.println("driver 생성중... " + (webDriverPool.size()+1) + "/" + MAX_POOL_SIZE);
		return driver;
	}
	
	public static WebDriver getWebDriver() {
		WebDriver driver = null;
		int maxRetries = 10;
		int retryCount = 0;
		
		while(driver == null && retryCount < maxRetries) {
			try {
				driver = webDriverPool.take();
			} catch (Exception e) {
				Thread.currentThread().interrupt();
				System.out.println("waiting driver...");
			}
		}
		System.out.println("드라이버를 빌려줍니다.\n남은 웹드라이버 : " + webDriverPool.size());
		return driver;
	}
	
	public static void releaseWebDriver(WebDriver webDriver) {
		webDriverPool.offer(webDriver);
		System.out.println("드라이버를 반환합니다.\n남은 웹드라이버 : " + webDriverPool.size());
	}
	
	
}
