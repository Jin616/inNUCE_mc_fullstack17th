package com.mc.innuce.domain.debate.dto;

import java.sql.Timestamp;

public class DebateMessage {
	private int opinion_key;
	private String user_id;
	private String opinion_contents;
	private Timestamp opinion_regdate;
	private int opinion_like;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOpinion_contents() {
		return opinion_contents;
	}
	public void setOpinion_contents(String opinion_contents) {
		this.opinion_contents = opinion_contents;
	}
	public Timestamp getOpinion_regdate() {
		return opinion_regdate;
	}
	public void setOpinion_regdate(Timestamp opinion_regdate) {
		this.opinion_regdate = opinion_regdate;
	}
	public int getOpinion_like() {
		return opinion_like;
	}
	public void setOpinion_like(int opinion_like) {
		this.opinion_like = opinion_like;
	}
	public int getOpinion_key() {
		return opinion_key;
	}
	public void setOpinion_key(int opinion_key) {
		this.opinion_key = opinion_key;
	}
	public DebateMessage(int opinion_key, String user_id, String opinion_contents, Timestamp opinion_regdate,
			int opinion_like) {
		super();
		this.opinion_key = opinion_key;
		this.user_id = user_id;
		this.opinion_contents = opinion_contents;
		this.opinion_regdate = opinion_regdate;
		this.opinion_like = opinion_like;
	}
	public DebateMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DebateMessage [opinion_key=" + opinion_key + ", user_id=" + user_id + ", opinion_contents="
				+ opinion_contents + ", opinion_regdate=" + opinion_regdate + ", opinion_like=" + opinion_like + "]";
	}
	
}
