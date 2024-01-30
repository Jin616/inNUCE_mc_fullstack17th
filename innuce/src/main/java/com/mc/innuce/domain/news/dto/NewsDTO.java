package com.mc.innuce.domain.news.dto;

import java.sql.Timestamp;

/**
 * @author JIN
 */
public class NewsDTO {
	
	private long news_key;
    private int press_key;
    private String news_title;
    private String news_content;
    private Timestamp news_writendate;
    private Timestamp news_updatedate;
    private Timestamp news_pulldate;
    private String news_writer;
    private String news_uri;
    private String news_originuri;
    private boolean news_deleted; 
    private Timestamp news_deleteddate; 
    private int news_deletedcode; 
    private String news_category; 
    private String news_thumbnailuri;
    private String news_thumbnailuri2;
	private String summ_content;
    private String summ_sentiment;
    private String summ_sentimentpercent;
    
	public long getNews_key() {
		return news_key;
	}
	public void setNews_key(long news_key) {
		this.news_key = news_key;
	}
	public int getPress_key() {
		return press_key;
	}
	public void setPress_key(int press_key) {
		this.press_key = press_key;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public Timestamp getNews_writendate() {
		return news_writendate;
	}
	public void setNews_writendate(Timestamp news_writendate) {
		this.news_writendate = news_writendate;
	}
	public Timestamp getNews_updatedate() {
		return news_updatedate;
	}
	public void setNews_updatedate(Timestamp news_updatedate) {
		this.news_updatedate = news_updatedate;
	}
	public Timestamp getNews_pulldate() {
		return news_pulldate;
	}
	public void setNews_pulldate(Timestamp news_pulldate) {
		this.news_pulldate = news_pulldate;
	}
	public String getNews_writer() {
		return news_writer;
	}
	public void setNews_writer(String news_writer) {
		this.news_writer = news_writer;
	}
	public String getNews_uri() {
		return news_uri;
	}
	public void setNews_uri(String news_uri) {
		this.news_uri = news_uri;
	}
	public String getNews_originuri() {
		return news_originuri;
	}
	public void setNews_originuri(String news_originuri) {
		this.news_originuri = news_originuri;
	}
	public boolean isNews_deleted() {
		return news_deleted;
	}
	public void setNews_deleted(boolean news_deleted) {
		this.news_deleted = news_deleted;
	}
	public Timestamp getNews_deleteddate() {
		return news_deleteddate;
	}
	public void setNews_deleteddate(Timestamp news_deleteddate) {
		this.news_deleteddate = news_deleteddate;
	}
	public int getNews_deletedcode() {
		return news_deletedcode;
	}
	public void setNews_deletedcode(int news_deletedcode) {
		this.news_deletedcode = news_deletedcode;
	}
	public String getNews_category() {
		return news_category;
	}
	public void setNews_category(String news_category) {
		this.news_category = news_category;
	}
	public String getNews_thumbnailuri() {
		return news_thumbnailuri;
	}
	public void setNews_thumbnailuri(String news_thumbnailuri) {
		this.news_thumbnailuri = news_thumbnailuri;
	}
	public String getNews_thumbnailuri2() {
		return news_thumbnailuri2;
	}
	public void setNews_thumbnailuri2(String news_thumbnailuri2) {
		this.news_thumbnailuri2 = news_thumbnailuri2;
	}
	public String getSumm_content() {
		return summ_content;
	}
	public void setSumm_content(String summ_content) {
		this.summ_content = summ_content;
	}
	public String getSumm_sentiment() {
		return summ_sentiment;
	}
	public void setSumm_sentiment(String summ_sentiment) {
		this.summ_sentiment = summ_sentiment;
	}
	public String getSumm_sentimentpercent() {
		return summ_sentimentpercent;
	}
	public void setSumm_sentimentpercent(String summ_sentimentpercent) {
		this.summ_sentimentpercent = summ_sentimentpercent;
	}
	
	@Override
	public String toString() {
		return "NewsDTO [news_key=" + news_key + ", press_key=" + press_key + ", news_title=" + news_title
				+ ", \nnews_content=" + news_content.substring(0, 20) + ", news_writendate=" + news_writendate + ", news_updatedate="
				+ news_updatedate + ", news_pulldate=" + news_pulldate + ", news_writer=" + news_writer + ", \nnews_uri="
				+ news_uri + ", news_originuri=" + news_originuri + ", \nnews_deleted=" + news_deleted
				+ ", news_deleteddate=" + news_deleteddate + ", news_deletedcode=" + news_deletedcode
				+ ", news_category=" + news_category + ", \nnews_thumbnailuri=" + news_thumbnailuri
				+ ", news_thumbnailuri2=" + news_thumbnailuri2 + ", \nsumm_content=" + summ_content + ", \nsumm_sentiment="
				+ summ_sentiment + ", summ_sentimentpercent=" + summ_sentimentpercent + "]";
	}

}
