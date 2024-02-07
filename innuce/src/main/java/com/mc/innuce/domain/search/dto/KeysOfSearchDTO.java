package com.mc.innuce.domain.search.dto;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class KeysOfSearchDTO {
	private int keyword_key;
	private String client_key;
	
	
	public KeysOfSearchDTO() {
		super();
	}
	public KeysOfSearchDTO(int keyword_key, String client_key) {
		super();
		this.keyword_key = keyword_key;
		this.client_key = client_key;
	}
	public int getKeyword_key() {
		return keyword_key;
	}
	public void setKeyword_key(int keyword_key) {
		this.keyword_key = keyword_key;
	}
	public String getClient_key() {
		return client_key;
	}
	public void setClient_key(String client_key) {
		this.client_key = client_key;
	}
	@Override
	public int hashCode() {
		return Objects.hash(client_key, keyword_key);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeysOfSearchDTO other = (KeysOfSearchDTO) obj;
		return Objects.equals(client_key, other.client_key) && keyword_key == other.keyword_key;
	}
	@Override
	public String toString() {
		return "KeysOfSearchDTO [keyword_key=" + keyword_key + ", client_key=" + client_key + "]";
	}
	
	
}
