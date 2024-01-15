package com.mc.innuce.domain.debate.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class OpinionDTO {
	private int opinion_key;
	private int debate_user_key;
	private String opinion_contents;
	private Timestamp opinion_regdate;
	private int opinion_like;
	public int getOpinion_key() {
		return opinion_key;
	}
	public void setOpinion_key(int opinion_key) {
		this.opinion_key = opinion_key;
	}
	public int getDebate_user_key() {
		return debate_user_key;
	}
	public void setDebate_user_key(int debate_user_key) {
		this.debate_user_key = debate_user_key;
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
	@Override
	public String toString() {
		return "OpinionDTO [opinion_key=" + opinion_key + ", debate_user_key=" + debate_user_key + ", opinion_contents="
				+ opinion_contents + ", opinion_regdate=" + opinion_regdate + ", opinion_like=" + opinion_like + "]";
	}
	public OpinionDTO(int opinion_key, int debate_user_key, String opinion_contents, Timestamp opinion_regdate,
			int opinion_like) {
		super();
		this.opinion_key = opinion_key;
		this.debate_user_key = debate_user_key;
		this.opinion_contents = opinion_contents;
		this.opinion_regdate = opinion_regdate;
		this.opinion_like = opinion_like;
	}
	public OpinionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
