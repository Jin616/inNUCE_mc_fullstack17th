package com.mc.innuce.domain.search.dto;

import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class KeywordDTO {
	private int keyword_key;
	private String keyword_content;
	private Timestamp keyword_recent_time;

	public KeywordDTO() {
		super();
	}

	public KeywordDTO(String keyword) {
		this.keyword_content = keyword;
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
		return "KeywordDTO [keyword_key=" + keyword_key + ", keyword_content=" + keyword_content
				+ ", keyword_recent_time=" + keyword_recent_time + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyword_content, keyword_key, keyword_recent_time);
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
				&& Objects.equals(keyword_recent_time, other.keyword_recent_time);
	}

}
