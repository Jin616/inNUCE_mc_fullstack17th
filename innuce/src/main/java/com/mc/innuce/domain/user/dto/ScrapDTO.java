package com.mc.innuce.domain.user.dto;

import org.springframework.stereotype.Component;

@Component
public class ScrapDTO {
	
	public int scrap_key;
	public int user_key;
	public long news_key;
	
	public ScrapDTO() {
	}

	public ScrapDTO(int scrap_key, int user_key, long news_key) {
		this.scrap_key = scrap_key;
		this.user_key = user_key;
		this.news_key = news_key;
	}

	public int getScrap_key() {
		return scrap_key;
	}

	public void setScrap_key(int scrap_key) {
		this.scrap_key = scrap_key;
	}

	public int getUser_key() {
		return user_key;
	}

	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}

	public long getNews_key() {
		return news_key;
	}

	public void setNews_key(int news_key) {
		this.news_key = news_key;
	}
	
	
	
	
}
