package com.mc.innuce.domain.debate.dto;

import org.springframework.stereotype.Component;

@Component
public class DebateUserDTO {
	private int debate_user_key;
	private int user_key;
	private int debate_room_key;

	public int getDebate_user_key() {
		return debate_user_key;
	}

	public void setDebate_user_key(int debate_user_key) {
		this.debate_user_key = debate_user_key;
	}

	public int getUser_key() {
		return user_key;
	}

	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}

	public int getDebate_room_key() {
		return debate_room_key;
	}

	public void setDebate_room_key(int debate_room_key) {
		this.debate_room_key = debate_room_key;
	}

	@Override
	public String toString() {
		return "DebateUserDTO [debate_user_key=" + debate_user_key + ", user_key=" + user_key + ", debate_room_key="
				+ debate_room_key + "]";
	}

	public DebateUserDTO(int debate_user_key, int user_key, int debate_room_key) {
		super();
		this.debate_user_key = debate_user_key;
		this.user_key = user_key;
		this.debate_room_key = debate_room_key;
	}

	public DebateUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
