package com.mc.innuce.domain.user.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
	private int user_key;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String email;
	private String phone;
	private boolean gender;
	private boolean user_is_deleted;
	private Timestamp user_deleted_time;
	private Timestamp birthday;
	private Timestamp regdate;
	public int getUser_key() {
		return user_key;
	}
	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public boolean isUser_is_deleted() {
		return user_is_deleted;
	}
	public void setUser_is_deleted(boolean user_is_deleted) {
		this.user_is_deleted = user_is_deleted;
	}
	public Timestamp getUser_deleted_time() {
		return user_deleted_time;
	}
	public void setUser_deleted_time(Timestamp user_deleted_time) {
		this.user_deleted_time = user_deleted_time;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "UserDTO [user_key=" + user_key + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_name="
				+ user_name + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", user_is_deleted="
				+ user_is_deleted + ", user_deleted_time=" + user_deleted_time + ", birthday=" + birthday + ", regdate="
				+ regdate + "]";
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(int user_key, String user_id, String user_pw, String user_name, String email, String phone,
			boolean gender, boolean user_is_deleted, Timestamp user_deleted_time, Timestamp birthday,
			Timestamp regdate) {
		super();
		this.user_key = user_key;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.user_is_deleted = user_is_deleted;
		this.user_deleted_time = user_deleted_time;
		this.birthday = birthday;
		this.regdate = regdate;
	}
	
}
