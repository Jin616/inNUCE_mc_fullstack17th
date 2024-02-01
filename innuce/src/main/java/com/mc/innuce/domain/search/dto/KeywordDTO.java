package com.mc.innuce.domain.search.dto;

import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class KeywordDTO {
	private int keyword_key;
	private String keyword_content;
	private Timestamp keyword_recent_time;
	private int search_count;
	
	
	public int getSearch_count() {
		return search_count;
	}

	public void setSearch_count(int search_count) {
		this.search_count = search_count;
	}

	public KeywordDTO() {
		super();
	}

	public KeywordDTO(String keyword) {
		this.keyword_content=keyword;
	}
	
	public KeywordDTO(int keyword_key) {
		this.keyword_key=keyword_key;
	}
	
	
	public int getKeyword_key() {
		return keyword_key;
	}
	public void setKeyword_key(int keyword_key) {
		this.keyword_key = keyword_key;
	}
	public String getKeyword_content() {
		return keyword_content;
	}
	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content;
	}
	public Timestamp getKeyword_recent_time() {
		return keyword_recent_time;
	}
	public void setKeyword_recent_time(Timestamp keyword_recent_time) {
		this.keyword_recent_time = keyword_recent_time;
	}

	@Override
	public String toString() {
		return "KeywordDTO [keyword_key=" + keyword_key + ", keyword_content=" + keyword_content + ", keyword_recent_time="
				+ keyword_recent_time + ", search_count=" + search_count + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyword_content, keyword_key, keyword_recent_time, search_count);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeywordDTO other = (KeywordDTO) obj;
		return Objects.equals(keyword_content, other.keyword_content) && keyword_key == other.keyword_key
				&& Objects.equals(keyword_recent_time, other.keyword_recent_time) && search_count == other.search_count;
	}

	
	
}
