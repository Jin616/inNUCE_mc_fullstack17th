package com.mc.innuce.domain.search.dto;
import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.stereotype.Component;
@Component
public class SearchDTO {
	private int search_key;
	private int keyword_key;
	private int user_key;
	private String client_key;
	private Timestamp search_time;
	private int search_age;
	private char search_gender;
	public int getSearch_key() {
		return search_key;
	}
	public void setSearch_key(int search_key) {
		this.search_key = search_key;
	}
	public int getKeyword_key() {
		return keyword_key;
	}
	public void setKeyword_key(int keyword_key) {
		this.keyword_key = keyword_key;
	}
	public int getUser_key() {
		return user_key;
	}
	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}
	public String getClient_key() {
		return client_key;
	}
	public void setClient_key(String client_key) {
		this.client_key = client_key;
	}
	public Timestamp getSearch_time() {
		return search_time;
	}
	public void setSearch_time(Timestamp search_time) {
		this.search_time = search_time;
	}
	public int getSearch_age() {
		return search_age;
	}
	public void setSearch_age(int search_age) {
		this.search_age = search_age;
	}
	public char getSearch_gender() {
		return search_gender;
	}
	public void setSearch_gender(char search_gender) {
		this.search_gender = search_gender;
	}
	@Override
	public String toString() {
		return "SearchDTO [search_key=" + search_key + ", keyword_key=" + keyword_key + ", user_key=" + user_key
				+ ", client_key=" + client_key + ", search_time=" + search_time + ", search_age=" + search_age
				+ ", search_gender=" + search_gender + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(client_key, keyword_key, search_age, search_gender, search_key, search_time, user_key);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchDTO other = (SearchDTO) obj;
		return Objects.equals(client_key, other.client_key) && keyword_key == other.keyword_key
				&& search_age == other.search_age && search_gender == other.search_gender && search_key == other.search_key
				&& Objects.equals(search_time, other.search_time) && user_key == other.user_key;
	}
	
	
}
