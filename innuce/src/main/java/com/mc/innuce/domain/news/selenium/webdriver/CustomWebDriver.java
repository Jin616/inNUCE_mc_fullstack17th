package com.mc.innuce.domain.news.selenium.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class CustomWebDriver extends ChromeDriver {

	private int moveCount = 0;
	private int errorFindElementCount = 0;
	private int errorFindElementsCount = 0;
	private int status;
	
	CustomWebDriver() {
		
	}
	
	@Override
	public void get(String url) {
		moveCount++;
		super.get(url);
	}
	
	
}
