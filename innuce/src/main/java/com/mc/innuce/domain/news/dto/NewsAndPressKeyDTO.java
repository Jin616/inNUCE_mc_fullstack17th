package com.mc.innuce.domain.news.dto;

/**
 * 
 * @author JIN
 *
 */
public class NewsAndPressKeyDTO {
	
	private String news_key;
	private String press_key;

	public String getNews_key() {
		return news_key;
	}
	public void setNews_key(String news_key) {
		this.news_key = news_key;
	}
	public String getPress_key() {
		return press_key;
	}
	public void setPress_key(String press_key) {
		this.press_key = press_key;
	}
	@Override
	public String toString() {
		return "NewsAndPressKeyDTO [news_key=" + news_key + ", press_key=" + press_key + "]";
	}
	
}
