package com.mc.innuce.domain.search.dto;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class KeywordNewsDTO {
	private int keyword_new_key;
	private String news_seq;
	private int keyword_key;

	@Override
	public int hashCode() {
		return Objects.hash(keyword_key, keyword_new_key, news_seq);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeywordNewsDTO other = (KeywordNewsDTO) obj;
		return keyword_key == other.keyword_key && keyword_new_key == other.keyword_new_key
				&& Objects.equals(news_seq, other.news_seq);
	}

	@Override
	public String toString() {
		return "KeywordNewsDTO [keyword_new_key=" + keyword_new_key + ", news_seq=" + news_seq + ", keyword_key="
				+ keyword_key + "]";
	}

	public int getKeyword_new_key() {
		return keyword_new_key;
	}

	public void setKeyword_new_key(int keyword_new_key) {
		this.keyword_new_key = keyword_new_key;
	}

	public String getNews_seq() {
		return news_seq;
	}

	public void setNews_seq(String news_seq) {
		this.news_seq = news_seq;
	}

	public int getKeyword_key() {
		return keyword_key;
	}

	public void setKeyword_key(int keyword_key) {
		this.keyword_key = keyword_key;
	}

}
