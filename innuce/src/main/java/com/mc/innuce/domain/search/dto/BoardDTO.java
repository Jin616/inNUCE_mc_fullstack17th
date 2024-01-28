package com.mc.innuce.domain.search.dto;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class BoardDTO {
	
//	seq int primary key auto_increment,
//  title varchar(100) not null,
//  content varchar(4000),
//  writer varchar(30),
//  viewCount int,
//  writingTime datetime
	
	int seq;
	String title, content, writer;
	int viewCount;
	String wirtingTime;
	
	
	public BoardDTO() {
		super();
	}
	public BoardDTO(String title, String content, String writer, int viewCount, String wirtingTime) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewCount = viewCount;
		this.wirtingTime = wirtingTime;
	}
	
	public BoardDTO(String title) {
		super();
		this.title = title;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getWirtingTime() {
		return wirtingTime;
	}
	public void setWirtingTime(String wirtingTime) {
		this.wirtingTime = wirtingTime;
	}
	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", content=" + content + ", writer=" + writer + ", viewCount="
				+ viewCount + ", wirtingTime=" + wirtingTime + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(content, seq, title, viewCount, wirtingTime, writer);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardDTO other = (BoardDTO) obj;
		return Objects.equals(content, other.content) && seq == other.seq && Objects.equals(title, other.title)
				&& viewCount == other.viewCount && Objects.equals(wirtingTime, other.wirtingTime)
				&& Objects.equals(writer, other.writer);
	}
	

}
