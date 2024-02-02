package com.mc.innuce.domain.debate.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class DebateRoomDTO {
	private int debate_room_key;
	private int keyword_key;
	private Timestamp debate_room_regdate;
	private String debate_room_name;
	private int debate_room_status;
	
	public int getDebate_room_key() {
		return debate_room_key;
	}
	public void setDebate_room_key(int debate_room_key) {
		this.debate_room_key = debate_room_key;
	}
	public int getKeyword_key() {
		return keyword_key;
	}
	public void setKeyword_key(int keyword_key) {
		this.keyword_key = keyword_key;
	}
	public Timestamp getDebate_room_regdate() {
		return debate_room_regdate;
	}
	public void setDebate_room_regdate(Timestamp debate_room_regdate) {
		this.debate_room_regdate = debate_room_regdate;
	}
	public String getDebate_room_name() {
		return debate_room_name;
	}
	public void setDebate_room_name(String debate_room_name) {
		this.debate_room_name = debate_room_name;
	}
	public int getDebate_room_status() {
		return debate_room_status;
	}
	public void setDebate_room_status(int debate_room_status) {
		this.debate_room_status = debate_room_status;
	}
	@Override
	public String toString() {
		return "DebateRoomDTO [debate_room_key=" + debate_room_key + ", keyword_key=" + keyword_key
				+ ", debate_room_regdate=" + debate_room_regdate
				+ ", debate_room_name=" + debate_room_name + "]";
	}
	public DebateRoomDTO(int debate_room_key, int keyword_key, Timestamp debate_room_regdate,
			String debate_room_name, int debate_room_status) {
		super();
		this.debate_room_key = debate_room_key;
		this.keyword_key = keyword_key;
		this.debate_room_regdate = debate_room_regdate;
		this.debate_room_name = debate_room_name;
		this.debate_room_status = debate_room_status;
	}
	public DebateRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
