package com.mc.innuce.domain.news.dto;

/**
 * 뉴스들을 파싱하기 전 필요한 임시 데이터를 저장하는 vo 객체 클래스
 * 
 * @author JIN
 *
 */
public class NewsTemVO {

	private String url;
	private String thumburl;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumburl() {
		return thumburl;
	}

	public void setThumburl(String thumburl) {
		this.thumburl = thumburl;
	}

	public NewsTemVO(String url, String thumburl) {
		this.url = url;
		this.thumburl = thumburl;
	}

	public NewsTemVO() {
	}

}
