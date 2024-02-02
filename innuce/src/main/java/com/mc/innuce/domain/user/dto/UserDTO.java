package com.mc.innuce.domain.user.dto;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {

	int user_key;
	String user_id, user_pw, user_name, email, phone, gender;
	boolean user_isdeleted, is_admin;
	String birthday, regdate, address, deleted_time;

	// Constructor
	public UserDTO() {
		super();
	}

	public UserDTO(int user_key, String user_id, String user_pw, String user_name, String email, String phone,
			String gender, boolean user_isdeleted, String deleted_time, String birthday, String regdate, String address,
			boolean is_admin) {
		super();
		this.user_key = user_key;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.user_isdeleted = user_isdeleted;
		this.birthday = birthday;
		this.regdate = regdate;
		this.address = address;
		this.deleted_time = deleted_time;
		this.is_admin = is_admin;
	}

	public UserDTO(String user_id, String user_pw, String user_name, String email, String phone, String gender,
			String birthday, String address) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	// Getter Setter
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isUser_isdeleted() {
		return user_isdeleted;
	}

	public void setUser_isdeleted(boolean user_isdeleted) {
		this.user_isdeleted = user_isdeleted;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeleted_time() {
		return deleted_time;
	}

	public void setDeleted_time(String deleted_time) {
		this.deleted_time = deleted_time;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

}
