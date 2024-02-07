package com.mc.innuce.domain.user.dto;

public class MailDTO {

    private String receiver;
    private String title;
    private String content;
    
    
    // 받는 메일 주소만 가지고 Constructor
	public MailDTO(String receiver) {
		this.receiver = receiver;
	}
	
	// getter setter
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
    
    
}