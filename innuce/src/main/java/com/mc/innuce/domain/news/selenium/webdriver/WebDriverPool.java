package com.mc.innuce.domain.news.selenium.webdriver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import com.mc.innuce.global.config.Config;

/**
 * Chrome 브라우저로 Selenium WebDriver 인스턴스를 관리하기 위한 Pool
 * 
 * @author JIN
 */
@Component
public class WebDriverPool {

	private static final int MAX_POOL_SIZE = 4;
	private static final BlockingQueue<WebDriver> webDriverPool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);

	static {
		for (int i = 0; i < MAX_POOL_SIZE; i++)
			webDriverPool.offer(createNewWebDriver());
	}

	public static WebDriver createNewWebDriver() {
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
		
		WebDriver driver;
		int retryCount = 0;
		while (true) {
			try {
				driver = new ChromeDriver(chromeOptions);
				Thread.sleep(500);
				
				break;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("retryCount : " + ++retryCount);
			}
		}
		return driver;
	}
	
	public static WebDriver getWebDriver() {
		WebDriver driver = null;
		
		try {
			driver = webDriverPool.take();
			
			if(driver == null) {
				System.out.println("uh, where is my driver? show me the driver");
				driver = createNewWebDriver();
			}
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		
		return driver;
	}

	public static void releaseWebDriver(WebDriver webDriver) {
		webDriverPool.offer(webDriver);
	}

}
