package com.mc.innuce.domain.search.dto;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class KeysDTO {
	int keyword_key;
	List<Long> newsKeyList;
	
	
	
	public KeysDTO() {
		super();
	}
	public KeysDTO(int keyword_key, List<Long> newsKeyList) {
		super();
		this.keyword_key = keyword_key;
		this.newsKeyList = newsKeyList;
	}
	public int getKeyword_key() {
		return keyword_key;
	}
	public void setKeyword_key(int keyword_key) {
		this.keyword_key = keyword_key;
	}
	public List<Long> getNewsKeyList() {
		return newsKeyList;
	}
	public void setNewsKeyList(List<Long> newsKeyList) {
		this.newsKeyList = newsKeyList;
	}
	@Override
	public int hashCode() {
		return Objects.hash(keyword_key, newsKeyList);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeysDTO other = (KeysDTO) obj;
		return keyword_key == other.keyword_key && Objects.equals(newsKeyList, other.newsKeyList);
	}
	@Override
	public String toString() {
		return "KeysDTO [keyword_key=" + keyword_key + ", newsKeyList=" + newsKeyList + "]";
	}
	
	
	
	
	

}
