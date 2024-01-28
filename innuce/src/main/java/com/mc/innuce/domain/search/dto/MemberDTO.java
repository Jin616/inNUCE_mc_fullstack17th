package com.mc.innuce.domain.search.dto;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {
	String id,pw;

	public MemberDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public MemberDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pw);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(pw, other.pw);
	}
	
}
