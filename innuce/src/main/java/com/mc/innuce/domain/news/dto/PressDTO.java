package com.mc.innuce.domain.news.dto;


/**
 * @author JIN
 */
public class PressDTO {
	
	private int press_key;
    private String press_name;
    private String press_logo;
    private String press_mainpage;
    private String press_navermain;
    
	public int getPress_key() {
		return press_key;
	}
	public void setPress_key(int press_key) {
		this.press_key = press_key;
	}
	public String getPress_name() {
		return press_name;
	}
	public void setPress_name(String press_name) {
		this.press_name = press_name;
	}
	public String getPress_logo() {
		return press_logo;
	}
	public void setPress_logo(String press_logo) {
		this.press_logo = press_logo;
	}
	public String getPress_mainpage() {
		return press_mainpage;
	}
	public void setPress_mainpage(String press_mainpage) {
		this.press_mainpage = press_mainpage;
	}
	public String getPress_navermain() {
		return press_navermain;
	}
	public void setPress_navermain(String press_navermain) {
		this.press_navermain = press_navermain;
	}
	
	@Override
	public String toString() {
		return "PressDTO [press_key=" + press_key + ", press_name=" + press_name + ", press_logo=" + press_logo
				+ ", press_mainpage=" + press_mainpage + ", press_navermain=" + press_navermain + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		PressDTO other = (PressDTO)obj;
		
		if (press_key != other.press_key) return false;
	    if (!press_name.equals(other.press_name)) return false;
	    if (!press_logo.equals(other.press_logo)) return false;
	    if (!press_mainpage.equals(other.press_mainpage)) return false;
	    if (!press_navermain.equals(other.press_navermain)) return false;
	    
		return true;
	}
}
