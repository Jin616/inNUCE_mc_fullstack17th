package com.mc.innuce.global.util.jsonparsefromdto;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.dto.PressDTO;

/**
 * DTO를 JSON으로 바꿔주는 유틸 클래스
 * 사용 예시 
 * JSONObject json = new JSONParse().getJSON(dto);
 * JSONArray jarray = new JSONParse().getJSONArray(dtolist);
 * @author JIN
 */
public class JSONParser {

	public JSONArray getJsonArrayNews(List<NewsDTO> newsList) {
		JSONArray jarray = new JSONArray();

		for (NewsDTO news : newsList)
			jarray.put(getJson(news));

		return jarray;
	}

	public JSONObject getJson(NewsDTO news) {
		JSONObject json = new JSONObject();

		json.put("news_key", news.getNews_key());
		json.put("press_key", news.getPress_key());
		json.put("news_title", news.getNews_title());
		json.put("news_content", news.getNews_content());
		json.put("news_writendate", news.getNews_writendate());
		json.put("news_updatedate", news.getNews_updatedate());
		json.put("news_pulldate", news.getNews_pulldate());
		json.put("news_writer", news.getNews_writer());
		json.put("news_uri", news.getNews_uri());
		json.put("news_originuri", news.getNews_originuri());
		json.put("news_deleted", news.isNews_deleted());
		json.put("news_deleteddate", news.getNews_deleteddate());
		json.put("news_deletedcode", news.getNews_deletedcode());
		json.put("news_category", news.getNews_category());
		json.put("news_thumbnailuri", news.getNews_thumbnailuri());
		json.put("news_thumbnailuri2", news.getNews_thumbnailuri2());
		json.put("summ_content", news.getSumm_content());
		json.put("summ_sentiment", news.getSumm_sentiment());
		json.put("summ_sentimentpercent", news.getSumm_sentimentpercent());

		return json;
	}

	public JSONArray getJsonArrayPress(List<PressDTO> pressList) {
		JSONArray jarray = new JSONArray();

		for (PressDTO press : pressList)
			jarray.put(getJson(press));

		return jarray;
	}

	public JSONObject getJson(PressDTO press) {
		JSONObject json = new JSONObject();

		json.put("press_key", press.getPress_key());
		json.put("press_name", press.getPress_name());
		json.put("press_logo", press.getPress_logo());
		json.put("press_mainpage", press.getPress_mainpage());
		json.put("press_navermain", press.getPress_navermain());

		return json;
	}

}
